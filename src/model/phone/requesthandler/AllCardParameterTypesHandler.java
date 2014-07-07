package model.phone.requesthandler;

import controller.phone.entity.AllCardParameterTypesRequest;
import controller.phone.entity.MobileRequest;
import model.additionalentity.phone.MobileParameterType;
import model.database.requests.ParameterRequest;
import model.exception.IllegalTypeException;
import model.phone.responseentity.AllCardParameterTypesResponse;
import model.phone.responseentity.MobileResponseEntity;

import java.util.ArrayList;

public class AllCardParameterTypesHandler implements MobileHandler {

    public AllCardParameterTypesResponse handleRequest(AllCardParameterTypesRequest allCardParameterTypesRequest) {
        AllCardParameterTypesResponse allCardParameterTypesResponse = new AllCardParameterTypesResponse();
        ArrayList<MobileParameterType> mobileParameterTypes = ParameterRequest.getAllMobileParameterTypes(allCardParameterTypesRequest);
        allCardParameterTypesResponse.setMobileParameterTypes(mobileParameterTypes);
        return allCardParameterTypesResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != AllCardParameterTypesRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, AllCardParameterTypesRequest.class);
        }
        AllCardParameterTypesRequest allCardParameterTypesRequest = (AllCardParameterTypesRequest) mobileRequest;
        return handleRequest(allCardParameterTypesRequest);
    }
}
