package org.xyz.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.xyz.core.component.domain.User;
import org.xyz.core.port.persistence.UserRepository;

import java.util.List;

@Slf4j
@ApplicationScoped
public class PanacheUserRepository implements UserRepository, PanacheRepository<User> {

    @Inject
    protected EntityManager em;

    @Override
    public List<User> getUsers() {
        return listAll();
    }

    @Override
    public User getUserById(long id) {
        return findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return find("email", email).firstResult();
    }

    @Override
    public long createUpdateUser(User user) {
        if(user.getId() != 0)
            em.merge(user);
        else
            persist(user);

        return user.getId();
    }

    @Override
    public boolean DeleteUser(long id) {
        return deleteById(id);
    }

    @Override
    public boolean login(String email, String password) {
       var user = getUserByEmail(email);

       if(user == null) return false;

       log.info("User {}",user.getEmail());

       return user.getPassword().equalsIgnoreCase(password);
    }
}
