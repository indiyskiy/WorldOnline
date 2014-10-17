package model.additionalentity.phone;


import javax.swing.*;
import java.util.ArrayList;

public class MobileCardRoute {
    private ArrayList<MobileRouteElement> mobileRouteElements;
    private ArrayList<MobileRouteCoordinate> mobileRouteCoordinates;

    public ArrayList<MobileRouteElement> getMobileRouteElements() {
        return mobileRouteElements;
    }

    public void setMobileRouteElements(ArrayList<MobileRouteElement> mobileRouteElements) {
        this.mobileRouteElements = mobileRouteElements;
    }

    public ArrayList<MobileRouteCoordinate> getMobileRouteCoordinates() {
        return mobileRouteCoordinates;
    }

    public void setMobileRouteCoordinates(ArrayList<MobileRouteCoordinate> mobileRouteCoordinates) {
        this.mobileRouteCoordinates = mobileRouteCoordinates;
    }
}
