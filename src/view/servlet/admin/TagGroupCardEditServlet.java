package view.servlet.admin;

import controller.parser.adminparser.TagGroupCardEditParser;
import model.constants.AdminRule;
import model.constants.Component;
import model.database.requests.TagRequest;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TagGroupCardEditServlet extends ProtectedServlet {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, TagGroupCardEditServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            TagGroupCardEditParser tagGroupCardEditParser = new TagGroupCardEditParser(request);
            if (tagGroupCardEditParser.getTagGroup() != null) {
                if (tagGroupCardEditParser.getCard() != null) {
                    if (tagGroupCardEditParser.getTagGroup().getCard() == null ||
                            !tagGroupCardEditParser.getTagGroup().getCard().getCardID().equals(tagGroupCardEditParser.getCard().getCardID())) {
                        TagRequest.setTagGroupCard(tagGroupCardEditParser.getTagGroup(), tagGroupCardEditParser.getCard());
                    }
                } else if (tagGroupCardEditParser.getTagGroup().getCard() != null) {
                    TagRequest.deleteTagGroupCard(tagGroupCardEditParser.getTagGroup());
                }
                ServletHelper.sendForward("/completetaggroupinfo?tagGroupID=" + tagGroupCardEditParser.getTagGroup().getTagGroupID(), this, request, response);
            }
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
