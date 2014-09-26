package view.servlet.admin;

import controller.parser.adminparser.AddCardParameterParser;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.ParameterRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.TextCardEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCardParameterServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddCardParameterServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AddCardParameterParser addCardParameterParser = new AddCardParameterParser(request);
            if (addCardParameterParser.getCardEntity() != null && addCardParameterParser.getCardParameterTypeEntity() != null) {
                if (addCardParameterParser.getCardParameterTypeEntity().isTranslatable()) {
                    TextCardEntity textCardEntity = TextRequest.addEmptyCardText(addCardParameterParser.getCardEntity(), addCardParameterParser.getCardParameterTypeEntity());
                } else {
                    ParameterRequest.addEmptyCardParameter(addCardParameterParser.getCardEntity(), addCardParameterParser.getCardParameterTypeEntity());
                }
                ServletHelper.sendForward("/completecardinfo?cardID=" + addCardParameterParser.getCardEntity().getCardID(), this, request, response);
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
