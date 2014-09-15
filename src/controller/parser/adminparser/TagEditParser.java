package controller.parser.adminparser;

import model.database.requests.TagRequest;
import model.database.worldonlinedb.TagEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class TagEditParser {
    private String errors = null;

    public TagEntity parse(HttpServletRequest request) {
        String tagIDString = request.getParameter("tagID");
        if (tagIDString == null || tagIDString.isEmpty()) {
            addError("Tag id(" + tagIDString + ") is incorrect");
            return null;
        }
        long tagID = Long.parseLong(tagIDString);
        if (tagID == 0) {
            addError("Tag id (" + tagID + ") is incorrect");
        }
        try {
            TagEntity oldTag = TagRequest.getTag(tagID);
            if (oldTag == null) {
                addError("Tag with id " + tagID + " was not found");
                return null;
            }
            String tagName = request.getParameter("TagName");
            if (tagName == null || tagName.replaceAll(" ", "").isEmpty()) {
                addError("Tag name can't be empty");
            } else {
                oldTag.setTagName(tagName);
            }
            return oldTag;
        } catch (SQLException e) {
            addError("Tag with id=" + tagID + " was not found");
            return null;
        }
    }

    private void addError(String errors) {
        if (this.errors == null) {
            this.errors = errors;
        } else {
            this.errors += "\n" + errors;
        }
    }

    public String getErrorsForHTML() {
        return errors.replaceAll("\n", "<br\\>");
    }

    public boolean hasNoErrors() {
        return (errors == null || errors.isEmpty());
    }

}
