package view.servlet.phone;

import controller.phone.entity.MobileRequest;
import controller.phone.parser.MobileParser;
import model.ServerInit;
import model.database.requests.UserRequests;
import model.exception.IllegalTypeException;
import model.exception.ParseRequestException;
import model.logger.LoggerFactory;
import model.phone.requesthandler.MobileHandler;
import model.phone.responseentity.MobileResponseEntity;
import helper.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public abstract class MobileServlet extends HttpServlet {
    private final MobileParser mobileParser = getMobileParser();
    private final MobileHandler mobileHandler = getMobileHandler();
    private final LoggerFactory loggerFactory = getLoggerFactory();
    private ServerInit serverInit = ServerInit.getInstance();

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            MobileRequest mobileRequest = mobileParser.parse(request);
            UserRequests.userOnline(mobileRequest.getUserID());
            MobileResponseEntity mobileResponseEntity = mobileHandler.handleRequest(mobileRequest);
            String responseString = mobileParser.getResponse(mobileResponseEntity);
            ServletHelper.sendJson(response, responseString);
        } catch (IllegalTypeException | ParseRequestException | IOException | ServletException | SQLException e) {
            ServletHelper.sendMobileError(loggerFactory, e, response);
        } catch (Exception e) {
            loggerFactory.error("unexpected error!!! emits in the blue spectrum! sore wa tenshi desu!");
            loggerFactory.error(e);
            ServletHelper.sendMobileError(loggerFactory, e, response);
        }
    }

    protected abstract MobileHandler getMobileHandler();

    public abstract MobileParser getMobileParser();

    protected abstract LoggerFactory getLoggerFactory();
}
