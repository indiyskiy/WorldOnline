package view.servlet.admin;

import controller.parser.adminparser.AddCategoryParser;
import model.constants.Component;
import model.database.requests.DishRequest;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.dishes.PriceEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCategoryServlet extends HttpServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddCategoryServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            AddCategoryParser addCatParser = new AddCategoryParser(request);
            DishRequest.addCategory(addCatParser);
            TextRequest.addText(addCatParser.getTextEntities());
            DishRequest.updatePrice(addCatParser.getPrice());
            ServletHelper.sendForward("/completepriceinfo?priceID=" + addCatParser.getPriceID(), this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
