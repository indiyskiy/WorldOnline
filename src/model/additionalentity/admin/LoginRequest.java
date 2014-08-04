package model.additionalentity.admin;

import helper.Md5Hash;

public class LoginRequest {
    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = Md5Hash.getMd5Hash(password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
