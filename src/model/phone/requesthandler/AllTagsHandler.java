package model.phone.requesthandler;

import controller.phone.entity.AllTagsRequest;
import controller.phone.entity.MobileRequest;
import model.database.requests.TagRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.AllTagsResponse;
import model.phone.responseentity.MobileResponseEntity;

import java.sql.SQLException;

public class AllTagsHandler implements MobileHandler {
    public AllTagsResponse handleRequest(AllTagsRequest allTagsRequest) throws IllegalTypeException, SQLException {
        AllTagsResponse allTagsResponse = new AllTagsResponse();
        allTagsResponse.setMobileTagGroups(TagRequest.getMobileTagGroups(allTagsRequest.getUserID()));
        return allTagsResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException, SQLException {
        if (mobileRequest.getClass() != AllTagsRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, AllTagsRequest.class);
        }
        AllTagsRequest allTagsRequest = (AllTagsRequest) mobileRequest;
        return handleRequest(allTagsRequest);
    }
}
