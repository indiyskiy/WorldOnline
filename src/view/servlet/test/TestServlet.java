package view.servlet.test;

import model.constants.AdminRule;
import model.constants.Component;
import model.logger.LoggerFactory;
import view.servlet.admin.ProtectedServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TestServlet extends ProtectedServlet {
    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, TestServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().print("do GET request, plz");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        DishRequest.addRandomDishTagDishLink();
    }


    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.AdminOnly;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
