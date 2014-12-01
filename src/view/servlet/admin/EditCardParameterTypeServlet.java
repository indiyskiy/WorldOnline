package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.ApplicationBlock;
import model.constants.Component;
import model.constants.databaseenumeration.DataType;
import model.database.requests.ParameterRequest;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.CardParameterTypeEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EditCardParameterTypeServlet extends ProtectedServlet {

    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, EditCardParameterTypeServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardParameterTypeID = Long.parseLong(request.getParameter("cardParameterTypeID"));
            boolean translate = Boolean.parseBoolean(request.getParameter("translate"));
            boolean multiply = Boolean.parseBoolean(request.getParameter("multiply"));
            DataType dataType = DataType.parseInt(Integer.parseInt(request.getParameter("dataType")));

            ApplicationBlock block = ApplicationBlock.parseInt(Integer.parseInt(request.getParameter("block")));
            CardParameterTypeEntity cardParameterTypeEntity = ParameterRequest.getCardParameterType(cardParameterTypeID);
            cardParameterTypeEntity.setMultiply(multiply);
            cardParameterTypeEntity.setTranslatable(translate);
            cardParameterTypeEntity.setDataType(dataType.getValue());
            if (block.getValue() != cardParameterTypeEntity.getBlock()) {
                cardParameterTypeEntity.setBlock(block.getValue());
                cardParameterTypeEntity.setPosition(ParameterRequest.getMaxNumber(block.getValue()) + 1);
            }
            ParameterRequest.editCardParameterType(cardParameterTypeEntity);
            UserDataRequest.updateParameterType();
            ServletHelper.sendForward("/completecardparametertypeinfo?cardParameterTypeID=" + cardParameterTypeID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
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
