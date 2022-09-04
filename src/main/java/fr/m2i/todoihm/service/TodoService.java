package fr.m2i.todoihm.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

import fr.m2i.todoihm.model.Todo;



@Component
public class TodoService {
	
	private Set<Todo> todos;
	
	
	public TodoService() {
		
		this.todos = new HashSet<Todo>(); 
		
	}

	public Set<Todo> getTodos() {
		return todos;
	}

	public void setTodos(Set<Todo> todos) {
		this.todos = todos;
	}
	
	
	
	public void addTodo(Todo todo) {
		this.todos.add(todo);
		
		
	}
	
	
	public void deleteTodo(Todo todo) {
		List<Todo> todos = new ArrayList<>();
		for(Todo todoItr: this.todos) {
			
			if(todoItr.getNom().equals(todo.getNom()) && todoItr.getDescription().equals(todo.getDescription()))
				todos.add(todoItr);
			
		}
		this.todos.removeAll(todos);
	}
	
	
	
	

}
