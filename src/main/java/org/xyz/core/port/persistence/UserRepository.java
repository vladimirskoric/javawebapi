package org.xyz.core.port.persistence;
import java.util.List;

import org.xyz.core.component.domain.User;

public interface UserRepository {
    List<User> getUsers();
    boolean DeleteUser(long id);
    User getUserById(long id);
    User getUserByEmail(String email);
    long createUpdateUser(User user);
    boolean login(String email, String password);
}
