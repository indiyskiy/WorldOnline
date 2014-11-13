package view.servlet.admin;

import model.constants.AdminRule;
import model.constants.ApplicationBlock;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.TagViewType;
import model.database.requests.TagRequest;
import model.database.requests.TextRequest;
import model.database.requests.UserDataRequest;
import model.database.worldonlinedb.TagGroupEntity;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;
import view.servlet.ServletHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateNewTagGroupServlet extends ProtectedServlet {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, CreateNewTagGroupServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            TagGroupEntity tagGroup = new TagGroupEntity();
            TextGroupEntity textGroup = new TextGroupEntity("TagGroupTextGroup");
            ApplicationBlock applicationBlock = ApplicationBlock.parseInt(Integer.parseInt(request.getParameter("block")));
            if (applicationBlock == null) {
                throw new ServletException("incorrect application block ID");
            }
            tagGroup.setTagGroupTextGroup(textGroup);
            tagGroup.setApplicationBlock(applicationBlock.getValue());
            tagGroup.setPosition(TagRequest.getMaxApplicationBlockPosition(applicationBlock));
            TagViewType tagViewType = TagViewType.parseInt(Integer.parseInt(request.getParameter("tagView")));
            if (tagViewType == null) {
                throw new ServletException("incorrect view type " + request.getParameter("tagView"));
            }
            tagGroup.setTagViewType(tagViewType.getValue());
            String name = request.getParameter("nameRu");
            if (name == null || name.replaceAll(" ", "").isEmpty()) {
                throw new ServletException("name is empty");
            }
            TextEntity textEntity = new TextEntity(LanguageType.Russian, name, textGroup);
            TextRequest.addText(textEntity);
            TagRequest.addTagGroup(tagGroup);
            UserDataRequest.updateTags();
            ServletHelper.sendForward("/completetaggroupinfo?tagGroupID=" + tagGroup.getTagGroupID(), this, request, response);
        } catch (Exception e) {
            ServletHelper.sendError(e, request, response, this, loggerFactory);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHelper.setUTF8(request, response);
        try {
            request.setAttribute("block", ApplicationBlock.getTagBlocks());
            request.setAttribute("tagView", TagViewType.Icons);
            ServletHelper.sendForward("/createnewtaggroup.jsp", this, request, response);
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
        return "Создание новой группы тегов";
    }
}
