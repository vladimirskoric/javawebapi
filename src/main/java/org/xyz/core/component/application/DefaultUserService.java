package org.xyz.core.component.application;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.xyz.core.component.application.dto.UserDTO;
import org.xyz.core.component.application.service.UserService;
import org.xyz.core.port.persistence.UserRepository;
import org.xyz.utils.Mapper;

@ApplicationScoped
public class DefaultUserService implements UserService {

    @Inject
    UserRepository repo;
    
    @Transactional
    @Override
    public long CreateUpdateUser(UserDTO userDto) {
        var user = Mapper.toUser(userDto);
        return repo.createUpdateUser(user);
    }

    @Transactional
    @Override
    public String RemoveUser(long id) {
        var user = repo.getUserById(id);

        if(user == null) return "User not found.";

        var result = repo.DeleteUser(id);
        return result ? "User deleted." : "User not deleted.";
    }

    @Transactional
    @Override
    public UserDTO GetUser(long id) {
        var user = repo.getUserById(id);
         
        if(user == null) return null;

        return Mapper.toUserDTO(user);
    }

    @Transactional
    @Override
    public List<UserDTO> GetUsers() {
       var users = repo.getUsers();
       
       if(users == null) return new ArrayList<UserDTO>();
        
       return Mapper.toUsers(users);
    }
}
