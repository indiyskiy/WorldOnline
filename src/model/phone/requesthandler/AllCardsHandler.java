package model.phone.requesthandler;

import controller.phone.entity.AllCardsRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.phone.MobileCardInfo;
import model.constants.Component;
import model.database.requests.CardRequest;
import model.exception.IllegalTypeException;
import model.logger.LoggerFactory;
import model.phone.responseentity.AllCardsResponse;
import model.phone.responseentity.MobileResponseEntity;

import java.util.LinkedList;


public class AllCardsHandler implements MobileHandler {
    LoggerFactory loggerFactory = new LoggerFactory(Component.Phone, AllCardsHandler.class);

    public AllCardsResponse handleRequest(AllCardsRequest allCardsRequest) {
        AllCardsResponse allCardsResponse = new AllCardsResponse();
        LinkedList<MobileCardInfo> mobileCardInfoList;
//        if (allCardsRequest.getLimit() != null && allCardsRequest.getOffset() != null) {
        mobileCardInfoList = CardRequest.getAllMobileCards(allCardsRequest.getUserID(), allCardsRequest.getLimit(), allCardsRequest.getOffset());
//        } else {
//            mobileCardInfoList = CardRequest.getAllMobileCards(allCardsRequest.getUserID());
//        }
        allCardsResponse.setCardList(mobileCardInfoList);
        return allCardsResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != AllCardsRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, AllCardsRequest.class);
        }
        AllCardsRequest allCardsRequest = (AllCardsRequest) mobileRequest;
        return handleRequest(allCardsRequest);
    }
}
