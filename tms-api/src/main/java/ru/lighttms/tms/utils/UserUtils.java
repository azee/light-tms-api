package ru.lighttms.tms.utils;

import ru.lighttms.tms.beans.User;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 * Date: 7/26/12
 * Time: 8:22 PM
 */

public class UserUtils {

    public static User copyUser(User source, User destin){
        if (source.getToken() != null && source.getToken() != ""){
            destin.setToken(source.getToken());
        }
        if (source.getId() != null && source.getId() != ""){
            destin.setId(source.getId());
        }
        if (source.getName() != null && source.getName() != ""){
            destin.setName(source.getName());
        }
        if (source.getSid() != null && source.getSid() != ""){
            destin.setSid(source.getSid());
        }
        if (source.getLogin() != null && source.getLogin() != ""){
            destin.setLogin(source.getLogin());
        }

        destin.setExpire(source.getExpire());

        return destin;
    }
}
