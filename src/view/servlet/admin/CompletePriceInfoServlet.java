package view.servlet.admin;

import model.additionalentity.admin.CompletePriceInfo;
import model.additionalentity.admin.SimpleCategory;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.DishRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class CompletePriceInfoServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CompletePriceInfoServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            ServletHelper.setUTF8(request, response);
            request.setAttribute("languages", LanguageType.values());
            Long priceID = Long.parseLong(request.getParameter("priceID"));
            CompletePriceInfo priceInfo = DishRequest.getCompletePriceInfo(priceID);
            ArrayList<SimpleCategory> simpleCategories = DishRequest.getPriceCategories(priceID);
            request.setAttribute("simpleCategories", simpleCategories);
            if (priceInfo != null) {
                request.setAttribute("price", priceInfo);
                request.setAttribute("title", cutTitle("Прайс [" +
                        priceInfo.getPriceID() +
                        "]"));

                ServletHelper.sendForward("/completepriceinfo.jsp?prcieID=" + priceID, this, request, response);
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
