package view.servlet.admin;

import model.additionalentity.admin.CompleteCardParameterTypeInfo;
import model.constants.AdminRule;
import model.constants.ApplicationBlock;
import model.constants.Component;
import model.constants.databaseenumeration.DataType;
import model.database.requests.ParameterRequest;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CompleteCardParameterTypeInfoServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompleteCardParameterTypeInfoServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            Long cardParameterTypeID = Long.parseLong(request.getParameter("cardParameterTypeID"));
            CompleteCardParameterTypeInfo completeCardParameterTypeInfo = ParameterRequest.getCompleteCardParameterTypeInfo(cardParameterTypeID);
            if (completeCardParameterTypeInfo != null) {
                request.setAttribute("cardParameterTypeInfo", completeCardParameterTypeInfo);
                request.setAttribute("title", cutTitle("Параметр [" +
                        completeCardParameterTypeInfo.getCardParameterTypeID() +
                        "]" +
                        completeCardParameterTypeInfo.getName()));
                request.setAttribute("dataTypes", DataType.values());
                request.setAttribute("blocks", ApplicationBlock.values());
                ServletHelper.sendForward("/completecardparametertypeinfo.jsp?cardParameterTypeID=" + cardParameterTypeID, this, request, response);
            }
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
        return null;
    }
}
