package view.servlet.admin;

import model.constants.ApplicationBlock;
import model.constants.Component;
import model.constants.databaseenumeration.DataType;
import model.database.requests.ParameterRequest;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.CardParameterTypeEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCardParameterTypeServlet extends HttpServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CreateCardParameterTypeServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            CardParameterTypeEntity cardParameterTypeEntity = new CardParameterTypeEntity();
            cardParameterTypeEntity.setBlock(ApplicationBlock.AdditionalInformation.getValue());
            cardParameterTypeEntity.setDataType(DataType.StringType.getValue());
            cardParameterTypeEntity.setTranslatable(false);
            cardParameterTypeEntity.setMultiply(false);
            cardParameterTypeEntity.setCardParameterTypeName(new TextGroupEntity("CardParameterTypeName"));
            cardParameterTypeEntity.setPosition(ParameterRequest.getMaxNumber(cardParameterTypeEntity.getBlock()) + 1);
            ParameterRequest.addCardParameterType(cardParameterTypeEntity);
            UserDataRequest.updateParameterType();
            ServletHelper.sendForward("/completecardparametertypeinfo?cardParameterTypeID=" + cardParameterTypeEntity.getCardParameterTypeID(), this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
