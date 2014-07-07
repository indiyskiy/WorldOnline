package model.phone.requesthandler;

import controller.phone.entity.AllMenuIDsRequest;
import controller.phone.entity.MobileRequest;
import controller.phone.entity.ParsedRegistrationRequest;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserContentEntity;
import model.database.worldonlinedb.UserEntity;
import model.database.worldonlinedb.UserHardwareEntity;
import model.database.worldonlinedb.UserPersonalDataEntity;
import model.exception.IllegalTypeException;
import model.phone.responseentity.MobileResponseEntity;
import model.phone.responseentity.RegistrationResponse;


public class UserRegistrationHandler implements MobileHandler {

    public RegistrationResponse handleRequest(ParsedRegistrationRequest parsedRegistrationRequest) {
        UserPersonalDataEntity userPersonalData = new UserPersonalDataEntity(parsedRegistrationRequest.getLanguage());
        UserHardwareEntity userHardware = new UserHardwareEntity(parsedRegistrationRequest.getDeviceID(),
                parsedRegistrationRequest.getDeviceToken(),
                parsedRegistrationRequest.getMobilePlatform());
        UserContentEntity userContent = UserContentEntity.getNewUserContentEntity();
        UserEntity userEntity = new UserEntity(userPersonalData, userHardware, userContent);
        UserRequests.addUser(userEntity);
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setUserID(userEntity.getUserID());
        return registrationResponse;
    }

    @Override
    public MobileResponseEntity handleRequest(MobileRequest mobileRequest) throws IllegalTypeException {
        if (mobileRequest.getClass() != ParsedRegistrationRequest.class) {
            throw new IllegalTypeException(MobileRequest.class, AllMenuIDsRequest.class);
        }
        ParsedRegistrationRequest parsedRegistrationRequest = (ParsedRegistrationRequest) mobileRequest;
        return handleRequest(parsedRegistrationRequest);
    }
}
