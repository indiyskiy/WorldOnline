package view.servlet.test;

import model.constants.Component;
import model.database.requests.UserRequests;
import model.logger.LoggerFactory;
import model.test.UserCardTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Илья on 24.03.14.
 */
public class UserCardTestServlet extends HttpServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, UserTestServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().print("do GET request, plz");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (UserRequests.countUser() > 0) {
            boolean success = UserCardTest.test();
            loggerFactory.info("UserCardTestServlet result is " + success);
            response.getOutputStream().print("UserCardTestServlet result is " + success);
        }
    }

}
