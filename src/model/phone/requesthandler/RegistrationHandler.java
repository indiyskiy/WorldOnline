package model.phone.requesthandler;

import controller.phone.entity.ParsedRegistrationRequest;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserContentEntity;
import model.database.worldonlinedb.UserEntity;
import model.database.worldonlinedb.UserHardwareEntity;
import model.database.worldonlinedb.UserPersonalDataEntity;
import model.phone.responseentity.RegistrationResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Servcer
 * Date: 28.02.14
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationHandler {

    public static RegistrationResponse handleRequest(ParsedRegistrationRequest parsedRegistrationRequest) {
        UserPersonalDataEntity userPersonalData=new UserPersonalDataEntity(parsedRegistrationRequest.getLanguage());
        UserHardwareEntity userHardware=new UserHardwareEntity(parsedRegistrationRequest.getDeviceID(),
                parsedRegistrationRequest.getDeviceToken(),
                parsedRegistrationRequest.getMobilePlatform());
        UserContentEntity userContent= UserContentEntity.getNewUserContentEntity();
        UserEntity userEntity=new UserEntity(userPersonalData,userHardware,userContent);
        UserRequests.addUser(userEntity);
        RegistrationResponse registrationResponse=new RegistrationResponse();
        registrationResponse.setUserID(userEntity.getUserId());
        return registrationResponse;
    }
}
