package org.xyz.core.component.application.service;

import java.util.List;
import org.xyz.core.component.application.dto.UserDTO;

public interface UserService {
    long CreateUpdateUser(UserDTO user);
    String RemoveUser(long id);
    UserDTO GetUser(long id);
    List<UserDTO> GetUsers();
}
