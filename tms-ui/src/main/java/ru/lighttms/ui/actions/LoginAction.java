package ru.lighttms.ui.actions;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */

public class LoginAction {

    private String message;

    private String userName;

    public LoginAction() {
    }

    public String execute() {
        setMessage("Hello " + getUserName());
        return "SUCCESS";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}