package controller.parser.adminparser;


import model.database.requests.CardRequest;
import model.database.requests.TagRequest;
import model.database.worldonlinedb.CardEntity;
import model.database.worldonlinedb.TagGroupEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class TagGroupCardEditParser {

    private TagGroupEntity tagGroup;
    private CardEntity card;

    public TagGroupCardEditParser(HttpServletRequest request) throws ServletException {
        String tagGroupIDString = request.getParameter("tagGroupID");
        if (tagGroupIDString == null || tagGroupIDString.isEmpty()) {
            throw new ServletException("tagGroupID is empty");
        }
        Long tagGroupID;
        try {
            tagGroupID = Long.parseLong(tagGroupIDString);
        } catch (NumberFormatException e) {
            throw new ServletException("tagGroupID is incorrect");
        }
        tagGroup = TagRequest.getTagGroup(tagGroupID);
        if (tagGroup == null) {
            throw new ServletException("tagGroup with id " + tagGroupID + " is not exist");
        }
        String cardIDString = request.getParameter("cardID").replaceAll(" ", "");
        if (cardIDString != null && !cardIDString.isEmpty()) {
            Long cardID;
            try {
                cardID = Long.parseLong(cardIDString);
            } catch (NumberFormatException e) {
                throw new ServletException("cardID is incorrect");
            }
            card = CardRequest.getCardByID(cardID);
            if (card == null) {
                throw new ServletException("card with id " + cardID + " is not exist");
            }
        }
    }

    public TagGroupEntity getTagGroup() {
        return tagGroup;
    }

    public void setTagGroup(TagGroupEntity tagGroup) {
        this.tagGroup = tagGroup;
    }

    public CardEntity getCard() {
        return card;
    }

    public void setCard(CardEntity card) {
        this.card = card;
    }
}
