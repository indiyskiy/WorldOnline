package view.servlet.admin;

import controller.parser.adminparser.CardPopularityParser;
import helper.ServletHelper;
import model.additionalentity.admin.CardPopularity;
import model.constants.AdminRule;
import model.constants.Component;
import model.constants.adminenumerations.CardPopularitySortBy;
import model.constants.databaseenumeration.CardState;
import model.database.requests.StatisticRequest;
import model.logger.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CardPopularityServlet extends ProtectedServlet {

    private LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CardPopularityServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletHelper.setUTF8(request, response);
            CardPopularityParser cardPopularityParser = new CardPopularityParser(50);
            cardPopularityParser.parse(request);
            ArrayList<CardPopularity> cardPopularities = StatisticRequest.getCardPopularity(cardPopularityParser);
            request.setAttribute("cardPopularities", cardPopularities);
            request.setAttribute("cardStates", new CardState[]{CardState.Active, CardState.NotActive, CardState.Deleted});
            request.setAttribute("sortBy", CardPopularitySortBy.values());
            ServletHelper.sendForward("/cardpopularity.jsp", this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    @Override
    protected AdminRule setAdminRule() {
        return AdminRule.Moderator;
    }

    @Override
    public String getTitle() {
        return "Популярность карточек";
    }

}
