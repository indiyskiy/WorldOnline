package model.phone.requesthandler;

import controller.phone.entity.MobileRequest;
import controller.phone.entity.CardImagesRequest;
import model.additionalentity.phone.MobileCardImagePair;
import model.database.requests.ImageRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.CardImagesResponse;
import model.phone.responseentity.MobileResponseEntity;

import java.util.LinkedList;

public class CardImageHandler implements MobileHandler {
    public CardImagesResponse handleRequest(CardImagesRequest cardImagesRequest) {
        CardImagesResponse cardImagesResponse = new CardImagesResponse();
        LinkedList<MobileCardImagePair> views = ImageRequest.getCardImages(cardImagesRequest.getImageType());
        cardImagesResponse.setMobileCardImagePairs(views);
        return cardImagesResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != CardImagesRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, CardImagesRequest.class);
        }
        CardImagesRequest cardImagesRequest = (CardImagesRequest) mobileRequest;
        return handleRequest(cardImagesRequest);
    }
}
