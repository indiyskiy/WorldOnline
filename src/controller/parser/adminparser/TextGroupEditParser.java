package controller.parser.adminparser;

import model.constants.Component;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.TextEntity;
import model.logger.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;

public class TextGroupEditParser {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, TextGroupEditParser.class);
    ArrayList<TextEntity> textEntityArrayList = new ArrayList<>();

    public TextGroupEditParser(HttpServletRequest request) throws ServletException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Enumeration<String> names = request.getParameterNames();
        HashSet<Long> ids = new HashSet<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            loggerFactory.debug("parse " + name);
            if (name.contains("id")) {
                ids.add(Long.parseLong(name.replaceAll("id", "")));
            }
        }
        Long textGroupID = null;
        for (Long id : ids) {
            TextEntity textEntity = TextRequest.getText(id);
            if (textEntity == null) {
                throw new ServletException("invalid text id " + id);
            }
            if (textEntity.getTextGroup() == null || textEntity.getTextGroup().getTextGroupID() == null) {
                throw new ServletException("invalid text group id " + id);
            }

            if (textGroupID != null) {
                if (!textGroupID.equals(textEntity.getTextGroup().getTextGroupID())) {
                    throw new ServletException("unstable text group id");
                }
            }
            if (textGroupID == null) {
                textGroupID = textEntity.getTextGroup().getTextGroupID();
            }
            String value = request.getParameter("text" + id);
            textEntity.setText(value);
            textEntityArrayList.add(textEntity);
            loggerFactory.debug("add " + value);
        }
    }

    public ArrayList<TextEntity> getTextEntityArrayList() {
        return textEntityArrayList;
    }
}
