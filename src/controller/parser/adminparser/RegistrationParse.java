package controller.parser.adminparser;

import helper.Md5Hash;
import helper.ParameterValidator;
import model.additionalentity.admin.ParsedRegistrationRequest;
import model.constants.databaseenumeration.DataType;
import model.database.requests.AdminUserRequest;
import model.database.worldonlinedb.AdminUserEntity;
import model.exception.ParseRequestException;

import javax.servlet.http.HttpServletRequest;

public class RegistrationParse {
    public static ParsedRegistrationRequest parse(HttpServletRequest request) throws ParseRequestException {
        ParsedRegistrationRequest parsedRegistrationRequest = new ParsedRegistrationRequest();
        String login = request.getParameter("login");
        if (login == null || login.isEmpty()) {
            throw new ParseRequestException("login is empty");
        }
        AdminUserEntity adminUserEntity = AdminUserRequest.getUserByLogin(login);
        if (adminUserEntity != null) {
            throw new ParseRequestException("login is already exist");
        }
        String pass1 = request.getParameter("password");
        String pass2 = request.getParameter("password2");
        if (pass1 == null || pass1.isEmpty() || pass2 == null || pass2.isEmpty()) {
            throw new ParseRequestException("password is empty");
        }
        if (!pass1.equals(pass2)) {
            throw new ParseRequestException("First and second passwords are not equals");
        }
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        if (firstName == null || firstName.isEmpty() || secondName == null || secondName.isEmpty()) {
            throw new ParseRequestException("first or second name is empty");
        }
        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            throw new ParseRequestException("email is empty");
        }
        String validParameter = ParameterValidator.isValidParameter(email, DataType.EmailType);
        if (validParameter == null) {
            throw new ParseRequestException("email is incorrect");
        }
        parsedRegistrationRequest.setLogin(login);
        parsedRegistrationRequest.setPassword(Md5Hash.getMd5Hash(pass1));
        parsedRegistrationRequest.setFirstName(firstName);
        parsedRegistrationRequest.setSecondName(secondName);
        parsedRegistrationRequest.setEmail(email);
        return parsedRegistrationRequest;
    }
}
