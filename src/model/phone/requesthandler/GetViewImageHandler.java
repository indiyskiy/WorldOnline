package model.phone.requesthandler;

import controller.phone.entity.GetViewImagesRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.ImageRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.GetViewImagesResponse;
import model.phone.responseentity.MobileViewImage;
import model.phone.responseentity.MobileResponseEntity;

import java.util.LinkedList;

public class GetViewImageHandler implements MobileHandler {
    public GetViewImagesResponse handleRequest(GetViewImagesRequest getViewImagesRequest) {
        GetViewImagesResponse getViewImagesResponse = new GetViewImagesResponse();
        LinkedList<MobileViewImage> views = ImageRequest.getViewCardImages();
        getViewImagesResponse.setMobileViewImages(views);
        return getViewImagesResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != GetViewImagesRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, GetViewImagesRequest.class);
        }
        GetViewImagesRequest getViewImagesRequest = (GetViewImagesRequest) mobileRequest;
        return handleRequest(getViewImagesRequest);
    }
}
