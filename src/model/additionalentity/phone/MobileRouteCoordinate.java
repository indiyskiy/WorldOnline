package model.additionalentity.phone;

public class MobileRouteCoordinate {
    private int position;
    private double latitude;
    private double longitude;
    private long mobileRouteCoordinateID;

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setMobileRouteCoordinateID(long mobileRouteCoordinateID) {
        this.mobileRouteCoordinateID = mobileRouteCoordinateID;
    }

    public long getMobileRouteCoordinateID() {
        return mobileRouteCoordinateID;
    }
}
