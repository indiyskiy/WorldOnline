package view.servlet.admin;

import controller.parser.adminparser.LoginParser;
import model.additionalentity.admin.LoginRequest;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.AdminUserRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Илья on 31.03.14.
 */
public class LoginServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            ServletHelper.sendForward("/login.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ServletHelper.setUTF8(request, response);
            LoginParser loginParser = new LoginParser();
            LoginRequest loginRequest = loginParser.parse(request);
            String key = AdminUserRequest.getKey(loginRequest);
            if (key == null) {
                request.setAttribute("exception", "incorrect username or password");
                ServletHelper.sendForward("/login.jsp", this, request, response);
                return;
            } else {
                AdminUserRequest.addSession(key, loginRequest.getLogin());
                request.getSession().setAttribute("sessionKey", key);
            }
            ServletHelper.sendForward("/index.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }


    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Unregistered;
    }


}
