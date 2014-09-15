package view.servlet.admin;

import model.constants.AdminRule;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends ProtectedServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.sendForward("/index.jsp", this, request, response);
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.All;
    }

    @Override
    public String getTitle() {
        return "Главная";
    }
}
