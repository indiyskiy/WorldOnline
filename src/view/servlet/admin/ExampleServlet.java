package view.servlet.admin;

import model.constants.AdminRule;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ExampleServlet extends ProtectedServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        ArrayList<UserEntity> allUsers = UserRequests.getAllUsers();
        request.setAttribute("users", allUsers);
        request.setAttribute("size", allUsers.size());
        ServletHelper.sendForward("/example.jsp", this, request, response);
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.All;
    }

    @Override
    public String getTitle() {
        return "Тестовая страница";
    }
}
