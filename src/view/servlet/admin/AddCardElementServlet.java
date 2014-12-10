package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.ApplicationBlock;
import model.constants.Component;
import model.database.requests.ParameterRequest;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCardElementServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddCardElementServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            request.setAttribute("blocks", ApplicationBlock.values());
            request.setAttribute("parameterTypes", ParameterRequest.getAllTypes());
            request.setAttribute("cardID", request.getParameter("cardID"));
            ServletHelper.sendForward("/addcardelement.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }

    @Override
    public String getTitle() {
        return "Добавить поле для карточки";
    }
}
