package org.acme.getting.started;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    
    public List<User> findByActive(Boolean active) {
        return find("active", active).list();
    }
    
    public User findByUsername(String username) {
        return find("username", username).firstResult();
    }
    
    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }
    
    public List<User> findByName(String name) {
        return find("fullName like ?1", "%" + name + "%").list();
    }
}