package view.servlet.admin;


import model.additionalentity.admin.ParameterType;
import model.constants.AdminRule;
import model.constants.Component;

import model.constants.databaseenumeration.TextType;
import model.database.requests.ParameterRequest;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AllCardParameterTypesServlet extends ProtectedServlet {

    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AllCardsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ArrayList<ParameterType> cardParameterTypeEntities = ParameterRequest.getAllTypes();
            request.setAttribute("cardParameterTypes", cardParameterTypeEntities);
            request.setAttribute("cardParameterTypesSize", cardParameterTypeEntities.size());
            request.setAttribute("textType", TextType.values());
            ServletHelper.sendForward("/allcardparametertypes.jsp", this, request, response);
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
        return "All card parameter types";
    }
}
