package controller.phone.entity;

/**
 * Created by Илья on 19.05.14.
 */
public class WeatherRequest extends MobileRequest {
    private Long userID;

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getUserID() {
        return userID;
    }
}
