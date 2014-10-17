package controller.parser.adminparser;

import model.additionalentity.CompleteTextGroupInfo;
import model.additionalentity.CompleteTextInfo;
import model.constants.Component;
import model.constants.databaseenumeration.LanguageType;
import model.database.requests.TextRequest;
import model.database.worldonlinedb.TextEntity;
import model.database.worldonlinedb.TextGroupEntity;
import model.logger.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class TextGroupEditParser {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Admin, TextGroupEditParser.class);
    Long textGroupID;
    ArrayList<CompleteTextInfo> completeTextInfos = new ArrayList<>();
    private ArrayList<TextEntity> textEntityUpdateArrayList = new ArrayList<>();
    private ArrayList<TextEntity> textEntityCreateArrayList = new ArrayList<>();
    private ArrayList<Long> textEntityDeleteArrayList = new ArrayList<>();

    public TextGroupEditParser(HttpServletRequest request) throws ServletException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        textGroupID = Long.parseLong(request.getParameter("textGroupID"));
        for (LanguageType languageType : LanguageType.values()) {
            String postfix = languageType.toString();
            String idString = request.getParameter("id" + postfix);
            CompleteTextInfo completeTextInfo = new CompleteTextInfo();
            if (idString != null && !idString.isEmpty()) {
                Long textID = Long.parseLong(idString);
                completeTextInfo.setTextID(textID);
            }
            String text = request.getParameter("text" + postfix);
            completeTextInfo.setText(text);
            completeTextInfo.setLanguageType(languageType);
            completeTextInfos.add(completeTextInfo);
        }
    }


    public ArrayList<TextEntity> getTextEntityUpdateArrayList() {
        return textEntityUpdateArrayList;
    }

    public ArrayList<TextEntity> getTextEntityCreateArrayList() {
        return textEntityCreateArrayList;
    }


    public ArrayList<Long> getTextEntityDeleteArrayList() {
        return textEntityDeleteArrayList;
    }

    public void formLists() throws ServletException {
        CompleteTextGroupInfo completeTextGroupInfo = TextRequest.getCompleteTextGroupInfo(textGroupID);
        if (completeTextGroupInfo != null) {
            TextGroupEntity textGroupEntity = TextRequest.getTextGroup(textGroupID);
            HashMap<Integer, CompleteTextInfo> completeTextInfoHashMap = completeTextGroupInfo.getTextMap();
            for (CompleteTextInfo completeTextInfo : completeTextInfos) {
                if (completeTextInfo.getTextID() == null || completeTextInfo.getTextID() == 0) {
                    if (completeTextInfoHashMap.get(completeTextInfo.getLanguageType().getValue()) != null) {
                        throw new ServletException("text id is null but text is exist");
                    } else {
                        if (completeTextInfo.getText() != null && !completeTextInfo.getText().isEmpty()) {
                            TextEntity textEntity = new TextEntity(completeTextInfo.getLanguageType(), completeTextInfo.getText(), textGroupEntity);
                            textEntityCreateArrayList.add(textEntity);
                        }
                    }
                } else {
                    Long textID = completeTextInfo.getTextID();
                    TextEntity textEntity = TextRequest.getText(textID);
                    if (completeTextInfo.getText() == null || completeTextInfo.getText().isEmpty()) {
                        textEntityDeleteArrayList.add(textEntity.getTextID());
                    } else {
                        if (!textEntity.getText().equals(completeTextInfo.getText())) {
                            textEntity.setText(completeTextInfo.getText());
                            textEntityUpdateArrayList.add(textEntity);
                        }
                    }
                }
            }
        }
    }
}
