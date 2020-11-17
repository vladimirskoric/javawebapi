package org.xyz.core.component.application;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.xyz.core.component.application.service.LoginService;
import org.xyz.core.port.persistence.UserRepository;

public class DefaultLoginService implements LoginService {

    @Inject
    UserRepository repo;

    @ApplicationScoped
    @Override
    public String login(String email, String password) {
        var authenticated = repo.login(email, password);
        return authenticated ? "Welcome..." : "Invalid user or password";
    }
    
}
