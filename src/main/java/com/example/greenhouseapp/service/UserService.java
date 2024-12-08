package com.example.greenhouseapp.service;
import com.example.greenhouseapp.model.User;
import java.util.List;

public interface UserService {
	User saveUser(User user);
	List<User> getAllUsers();
	User getUserById(long id);
	User updateUser(User user, long id);
	void deleteUser(long id);
}