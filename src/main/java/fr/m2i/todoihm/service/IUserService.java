package fr.m2i.todoihm.service;

import fr.m2i.todoihm.model.User;

public interface IUserService {
	User findByUsername(String username);
}
