package fr.m2i.todoihm.configuration;


import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import fr.m2i.todoihm.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	CustomUserDetailsService cuds;
	
	
	 @Bean
	 public SecurityFilterChain filterChainWeb(HttpSecurity http) throws Exception {
		 
		 //.anyRequest().authenticated()
		// .anyRequest().permitAll()
		 http.authorizeRequests()
			.antMatchers("/admin/").hasRole("ADMIN")
			.antMatchers("/user/").hasAnyRole("USER", "ADMIN")
			.and().exceptionHandling().accessDeniedPage("/forbidden")
				 .and()
			.formLogin(form -> form.loginPage("/login"))
				 .logout(form -> form.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("JSESSIONID"));
		 return http.build();
	  }
	@Bean
	@Order(1)
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		http
				.antMatcher("/api/**")
				.authorizeHttpRequests(authorize -> authorize
						.anyRequest().hasRole("ADMIN")
				)
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	 
	 
	
	 @Bean
	 public DaoAuthenticationProvider jpaDaoAuthProvider() {
		 DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		 daoAuthenticationProvider.setUserDetailsService(cuds);
		 daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		 
		 
		 return daoAuthenticationProvider;
		 
	 }
	 
	 @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	 
	/*  @Bean
	    public InMemoryUserDetailsManager userDetailsService() {
	         
	    	 UserDetails user1 = User.withDefaultPasswordEncoder()
	             .username("user")
	             .password("user")
	             .roles("USER")
	             .build();
	    	
	    	 UserDetails user2 = User.withDefaultPasswordEncoder()
		             .username("admin")
		             .password("admin")
		             .roles("ADMIN")
		             .build();
	    	 
	    	 
	    	 ArrayList<UserDetails> users = new ArrayList<>();
	    	 
	    	 users.add(user2);
	    	 users.add(user1);
	        
	         return new InMemoryUserDetailsManager(users);
	     }*/


	 
}
