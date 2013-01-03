package ru.lighttms.tms.api.repositories.custom;

import ru.lighttms.tms.beans.User;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */
public interface UserRepositoryCustom {

    public User authoriseUser(User user);
    public User saveUser(User user);
    public User getUserByName (String name);
    public User getUserByToken (String token);
    public void removeUserSession (String token);

}
