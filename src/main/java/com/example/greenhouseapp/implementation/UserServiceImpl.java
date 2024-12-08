package com.example.greenhouseapp.implementation;

import com.example.greenhouseapp.model.User;
import com.example.greenhouseapp.repository.UserRepository;
import com.example.greenhouseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User updateUser(User user, long id) {
		User existingUser = userRepository.findById(id).orElse(null);
		if (existingUser != null) {
			existingUser.setName(user.getName());
			existingUser.setPassword(user.getPassword());
			userRepository.save(existingUser);
			return existingUser;
		}
		return null;
	}

	@Override
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
}