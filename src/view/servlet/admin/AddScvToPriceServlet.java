package view.servlet.admin;

import controller.parser.adminparser.AddScvToPriceParser;
import model.constants.Component;
import model.database.requests.DishRequest;
import model.logger.LoggerFactory;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddScvToPriceServlet extends HttpServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, ImageCardUploadServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            AddScvToPriceParser addScvToPriceParser = new AddScvToPriceParser(request);
            addScvToPriceParser.runAdding();
            DishRequest.updatePrice(addScvToPriceParser.getPriceEntity());
            ServletHelper.sendForward("/completepriceinfo?priceID=" + addScvToPriceParser.getPriceID(), this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }
}
