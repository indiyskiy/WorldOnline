package view.servlet.test;

import model.constants.Component;
import model.database.requests.UserRequests;
import model.logger.LoggerFactory;
import model.test.UserTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Илья on 24.03.14.
 */
public class UserTestServlet extends HttpServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, UserTestServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().print("do GET request, plz");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (UserRequests.countUser() < 2) {
            boolean success = UserTest.test();
            loggerFactory.info("UserTestServlet result is " + success);
            response.getOutputStream().print("UserTestServlet result is " + success);
        }
    }
}
