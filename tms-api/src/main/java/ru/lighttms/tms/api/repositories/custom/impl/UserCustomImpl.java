package ru.lighttms.tms.api.repositories.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.lighttms.tms.api.repositories.custom.UserRepositoryCustom;
import ru.lighttms.tms.beans.User;
import ru.lighttms.tms.utils.UserUtils;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */
public class UserCustomImpl implements UserRepositoryCustom{
    @Autowired
    private MongoOperations mongoOperations;


    @Override
    public User authoriseUser(User user) {
           Query query = new Query();

           query.addCriteria(Criteria.where("name").is(user.getName()));

           User dbUser = mongoOperations.findOne(query, User.class, "tms_users");

           if (dbUser == null){
               mongoOperations.insert(user, "tms_users");
               dbUser = user;
           }
           else {
               dbUser.setToken(user.getToken());
               dbUser.setExpire(user.getExpire());
               mongoOperations.save(dbUser, "tms_users");
           }

           return dbUser;
       }

    @Override
    public User saveUser(User user){
        if (user.getName() == null || user.getName().equals("")){
            throw new RuntimeException("Username is not defined");
        }
        Query query = new Query();

        query.addCriteria(Criteria.where("name").is(user.getName()));
        User dbUser = mongoOperations.findOne(query, User.class, "tms_users");

        if (dbUser == null){
            mongoOperations.insert(user, "tms_users");
            dbUser = user;
        }
        else {
            mongoOperations.save(UserUtils.copyUser(user, dbUser), "tms_users");
        }
        return dbUser;
    }

    @Override
    public User getUserByName (String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoOperations.findOne(query, User.class, "tms_users");
    }

    @Override
    public User getUserByToken (String token){
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        return mongoOperations.findOne(query, User.class, "tms_users");
    }

    @Override
    public void removeUserSession (String token){
        Query query = new Query();
        query.addCriteria(Criteria.where("token").is(token));
        User user = mongoOperations.findOne(query, User.class, "tms_user");
        if (user != null){
            user.setToken("");
            user.setExpire(0);
            mongoOperations.save(user, "tms_users");
        }
    }

}
