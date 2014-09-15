package view.servlet.admin;

import model.additionalentity.admin.TagGroup;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.TagRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AddTagToCardServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, AddTagToCardServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Map m = request.getParameterMap();
            Set s = m.entrySet();
            long cardID = 0L;
            HashSet<Long> tagIDsFromRequest = new HashSet<>();
            for (Object parameter : s) {
                Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) parameter;
                if ("cardID".equals(entry.getKey())) {
                    cardID = Long.parseLong(entry.getValue()[0]);
                }
                if ("tagID".equals(entry.getKey())) {
                    for (String tagIDString : entry.getValue()) {
                        Long tagID = Long.parseLong(tagIDString);
                        tagIDsFromRequest.add(tagID);
                    }
                }
            }
            if (cardID != 0L) {
                HashSet<Long> cardTagIDs = TagRequest.getCardTagIDs(cardID);
                ArrayList<Long> idsToAdd = new ArrayList<>();
                ArrayList<Long> idsToDelete = new ArrayList<>();
                for (Long id : tagIDsFromRequest) {
                    if (!cardTagIDs.contains(id)) {
                        idsToAdd.add(id);
                    }
                }
                for (Long id : cardTagIDs) {
                    if (!tagIDsFromRequest.contains(id)) {
                        idsToDelete.add(id);
                    }
                }
                TagRequest.deleteTagsFromCard(cardID, idsToDelete);
                TagRequest.addTagsToCard(cardID, idsToAdd);
            }
            ServletHelper.sendForward("/completecardinfo?cardID=" + cardID, this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            Long cardID = Long.parseLong(request.getParameter("cardID"));
            Collection<TagGroup> tagGroups = TagRequest.getAllTagGroups(cardID);
            request.setAttribute("tagGroups", tagGroups);
            request.setAttribute("cardID", cardID);
            ServletHelper.sendForward("/addtagtocard.jsp?cardID=" + cardID, this, request, response);
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
        return "Добавить тег к карточке";
    }
}
