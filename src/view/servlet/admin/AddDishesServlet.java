package view.servlet.admin;

import controller.parser.adminparser.AddDishesParser;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.DishRequest;
import model.database.requests.TextRequest;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDishesServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ProtectedServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            AddDishesParser addDishesParser = new AddDishesParser(request);
            DishRequest.addDishesSQL(addDishesParser.getDishList());
            TextRequest.addText(addDishesParser.getTextEntities());
            DishRequest.updatePrice(DishRequest.getPrice(addDishesParser.getPriceID()));
            ServletHelper.sendForward("/completepriceinfo?priceID=" + addDishesParser.getPriceID(), this, request, response);
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
