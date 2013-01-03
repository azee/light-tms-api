package ru.lighttms.tms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ru.lighttms.tms.api.repositories.UserRepository;
import ru.lighttms.tms.beans.User;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: azee
  */

@Path("/user")
public class UserService {

    @Value("#{domain['token.timeout']}")
    private long TOKEN_TIMEOUT;

    @Value("#{domain['sid.coockie.name']}")
    private String SID_COOKIE_NAME;

    @Autowired
    UserRepository userRepository;

    @GET
    @Path("/authorise")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User authorise(
            @QueryParam("login") final String login,
            @QueryParam("pass") final String pass,
            @Context HttpServletRequest hsr) throws Exception {
        String token = "";

        Date date = new Date();

        //Prepare a user object
        User user = new User();
        user.setName(login);
        user.setToken(token);
        user.setExpire(date.getTime() + TOKEN_TIMEOUT);

        //Saving user data to mongo
        userRepository.authoriseUser(user);
        return user;
    }


    @GET
    @Path("/sid")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User checkSid(@QueryParam("cookie") final String cookie, @Context HttpServletRequest hsr) throws Exception {
        Cookie sidCookie = null;
        //Searching for Session_id cookie
        if (hsr != null && hsr.getCookies() != null){
            for (Cookie coocie : hsr.getCookies()){
                if (coocie.getName().equals(SID_COOKIE_NAME)){
                    sidCookie = coocie;
                }
            }
        }

        //Return if user is not authorised
        if (sidCookie == null && (cookie == null || cookie.equals(""))){
            return new User();
        }

        //Check custom send sid
        if (sidCookie == null){
            sidCookie = new Cookie(SID_COOKIE_NAME, cookie);
        }


        //Prepare a user object
        User user = new User();

        user.setToken(sidCookie.getValue().hashCode() + "-" + new Date().getTime());

        //Saving user data to mongo
        return userRepository.authoriseUser(user);
    }

    @GET
    @Path("/token")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getUserByToken(@QueryParam("token") final String token) throws Exception {
        return new User();
    }

    @GET
    @Path("/logout")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User logoutToken(@QueryParam("token") final String token) throws Exception {
        userRepository.removeUserSession(token);
        return new User();
    }


}
