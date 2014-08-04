package view.servlet.admin;

import controller.parser.adminparser.RegistrationParse;
import model.additionalentity.admin.ParsedRegistrationRequest;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.AdminUserRequest;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;
import model.mailer.RegistrationMailer;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, RegistrationServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            ServletHelper.sendForward("/registration.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ParsedRegistrationRequest parsedRegistrationRequest = RegistrationParse.parse(request);
            AdminUserRequest.registUser(parsedRegistrationRequest);
            RegistrationMailer.sendRegistrationMail(parsedRegistrationRequest);
        } catch (ParseRequestException e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Unregistered;
    }

}
