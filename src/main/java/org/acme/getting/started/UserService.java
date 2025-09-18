package org.acme.getting.started;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.listAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getActiveUsers() {
        return userRepository.findByActive(true);
    }

    public List<User> getInactiveUsers() {
        return userRepository.findByActive(false);
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    public User createUser(User user) {
        userRepository.persist(user);
        return user;
    }

    @Transactional
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setFullName(userDetails.getFullName());
            user.setAge(userDetails.getAge());
            user.setActive(userDetails.getActive());
        }
        return user;
    }

    @Transactional
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }
}