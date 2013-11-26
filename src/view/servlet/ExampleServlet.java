package view.servlet;

import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: Graf_D
 * Date: 17.10.13
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */
public class ExampleServlet extends HttpServlet {

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

}
