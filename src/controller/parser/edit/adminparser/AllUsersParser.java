package controller.parser.edit.adminparser;

import model.constants.databaseenumeration.MobilePlatform;
import view.servlet.ServletHelper;

import javax.servlet.http.HttpServletRequest;
import java.security.Timestamp;

/**
 * Created by Илья on 13.03.14.
 */
public class AllUsersParser {
    private int maxItems = 0;
    int firstElem = 0;
    Long userID = null;
    MobilePlatform mobilePlatform = null;
    Timestamp maxTimeStampUpdate = null;
    Timestamp minTimeStampUpdate = null;
    Timestamp maxTimeStampRegistration = null;
    Timestamp minTimeStampRegistration = null;
    Timestamp maxTimeStampEnter = null;
    Timestamp minTimeStampEnter = null;

    public AllUsersParser(int maxItems) {
        this.maxItems = maxItems;
    }

    public int getMaxItems() {
        return maxItems;
    }

    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }

    public int getFirstElem() {
        return firstElem;
    }

    public void setFirstElem(int firstElem) {
        this.firstElem = firstElem;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public MobilePlatform getMobilePlatform() {
        return mobilePlatform;
    }

    public void setMobilePlatform(MobilePlatform mobilePlatform) {
        this.mobilePlatform = mobilePlatform;
    }

    public Timestamp getMaxTimeStampUpdate() {
        return maxTimeStampUpdate;
    }

    public void setMaxTimeStampUpdate(Timestamp maxTimeStampUpdate) {
        this.maxTimeStampUpdate = maxTimeStampUpdate;
    }

    public Timestamp getMinTimeStampUpdate() {
        return minTimeStampUpdate;
    }

    public void setMinTimeStampUpdate(Timestamp minTimeStampUpdate) {
        this.minTimeStampUpdate = minTimeStampUpdate;
    }

    public Timestamp getMaxTimeStampRegistration() {
        return maxTimeStampRegistration;
    }

    public void setMaxTimeStampRegistration(Timestamp maxTimeStampRegistration) {
        this.maxTimeStampRegistration = maxTimeStampRegistration;
    }

    public Timestamp getMinTimeStampRegistration() {
        return minTimeStampRegistration;
    }

    public void setMinTimeStampRegistration(Timestamp minTimeStampRegistration) {
        this.minTimeStampRegistration = minTimeStampRegistration;
    }

    public Timestamp getMaxTimeStampEnter() {
        return maxTimeStampEnter;
    }

    public void setMaxTimeStampEnter(Timestamp maxTimeStampEnter) {
        this.maxTimeStampEnter = maxTimeStampEnter;
    }

    public Timestamp getMinTimeStampEnter() {
        return minTimeStampEnter;
    }

    public void setMinTimeStampEnter(Timestamp minTimeStampEnter) {
        this.minTimeStampEnter = minTimeStampEnter;
    }

    public void parse(HttpServletRequest request) {
//        if (request.getParameter("UserIDRe") != null && !request.getParameter("UserIDRe").isEmpty()) {
//            userID = Long.parseLong(ServletHelper.getAndSetAttribute(request, "UserIDRe"));
//        }
//
//        if (request.getParameter("MobilePlatformRe") != null && !request.getParameter("MobilePlatformRe").isEmpty()) {
//            mobilePlatform = MobilePlatform.parseInt(Integer.parseInt(ServletHelper.getAndSetAttribute(request, "MobilePlatformRe")));
//        }
        if (request.getParameter("Page") != null && !request.getParameter("Page").isEmpty()) {
            firstElem = maxItems * Integer.parseInt((ServletHelper.getAndSetAttribute(request, "Page")));
        }
    }

    public boolean haveMatter() {
        if (userID != null) {
            return true;
        }
        if (mobilePlatform != null) {
            return true;
        }
        if (minTimeStampEnter != null) {
            return true;
        }
        if (maxTimeStampRegistration != null) {
            return true;
        }
        if (minTimeStampRegistration != null) {
            return true;
        }
        if (maxTimeStampUpdate != null) {
            return true;
        }
        if (minTimeStampUpdate != null) {
            return true;
        }
        return false;

    }
}
