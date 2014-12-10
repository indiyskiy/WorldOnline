package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.TextCardEntity;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteTextCardServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, DeleteTextCardServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            Long textCardID = Long.parseLong(request.getParameter("textCardID"));
            TextCardEntity textCardEntity = TextRequest.getTextCard(textCardID);
            if (textCardEntity != null) {
                CardRequest.updateCard(textCardEntity.getCard());
                TextRequest.deleteTextCard(textCardEntity);
            }
            ServletHelper.sendForward("/completecardinfo?cardID=" + request.getParameter("cardID"), this, request, response);
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
