package com.example.greenhouseapp.service;

import com.example.greenhouseapp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    public User findByName(String name) {
        try {
            return entityManager.createQuery(	"SELECT u FROM User u WHERE u.name = :name", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            return null; // User not found
        }
    }

    public void registerUser(String name, String password) {
        if (findByName(name) != null) {
            throw new IllegalArgumentException("Name already exists");
        }

        User user = new User(name, password);
        entityManager.persist(user);
    }

    public boolean authenticate(String name, String password) {
        User user = findByName(name);
        return user != null && user.getPassword().equals(password);
    }
}