package model.additionalentity;

import model.Md5Hash;
import model.database.requests.AdminUserRequest;

/**
 * Created by Илья on 07.04.14.
 */
public class ParsedRegistrationRequest {

    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getHashMd5() {
        String str = AdminUserRequest.getMd5Hash(login, email, password, firstName, secondName);
        return str;
    }

    public void setPasswordFromOriginal(String s) {
        password = Md5Hash.getMd5Hash(s);
    }
}
