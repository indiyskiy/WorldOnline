package controller.phone.entity;


public class RestoreUserRequest extends MobileRequest {
    private String deviceID;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}
