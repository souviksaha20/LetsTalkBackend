package com.niit.LetsTalkBackend.Dao;

import java.util.List;

import com.niit.LetsTalkBackend.Model.User;

public interface UserDao {
	User authenticate(User user);
	void updateUser(User user);
	User registerUser(User user);
	public List<User> getAllUsers(User user);
}
