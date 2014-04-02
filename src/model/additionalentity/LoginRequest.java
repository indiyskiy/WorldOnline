package model.additionalentity;

import model.Md5Hash;

/**
 * Created by Илья on 31.03.14.
 */
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
