package model.phone.requesthandler;

import controller.phone.entity.ViewImagesRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.ImageRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.ViewImagesResponse;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.MobileViewImage;

import java.util.LinkedList;

public class ViewImageHandler implements MobileHandler {
    public ViewImagesResponse handleRequest(ViewImagesRequest viewImagesRequest) {
        ViewImagesResponse viewImagesResponse = new ViewImagesResponse();
        LinkedList<MobileViewImage> views = ImageRequest.getViewCardImages();
        viewImagesResponse.setMobileViewImages(views);
        return viewImagesResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != ViewImagesRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, ViewImagesRequest.class);
        }
        ViewImagesRequest viewImagesRequest = (ViewImagesRequest) mobileRequest;
        return handleRequest(viewImagesRequest);
    }
}
