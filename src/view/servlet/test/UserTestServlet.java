package view.servlet.test;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.UserRequests;
import model.logger.LoggerFactory;
import model.test.UserTest;
import view.servlet.admin.ProtectedServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Илья on 24.03.14.
 */
public class UserTestServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, UserTestServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().print("do GET request, plz");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean success = UserTest.test();
        response.getOutputStream().print("UserTestServlet result is " + success);
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.AdminOnly;
    }
}
