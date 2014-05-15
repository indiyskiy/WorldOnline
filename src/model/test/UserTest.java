package model.test;

import helper.HTTPRequestSender;
import helper.Md5Hash;
import model.constants.Component;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MobilePlatform;
import model.database.requests.UserRequests;
import model.logger.LoggerFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Илья on 14.03.14.
 */
public class UserTest {
    private static final String url = ServerConsts.GlobalServerURL + ServerConsts.worldOnlineModule + ServerConsts.mobileModule + "userregistration";
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Test, UserTest.class);
    public static int counter = 0;

    private static void addManyUsers(int number) {
        for (int i = 0; i < number; i++) {
            counter++;
            Thread thread = new Thread(new RegEntity(i));
            thread.start();
        }
    }

    private static ArrayList<NameValuePair> getParameters(int number) {
        ArrayList<NameValuePair> requestList = new ArrayList<NameValuePair>();
        requestList.add(new BasicNameValuePair("model", "somerandommodel" + number));
        requestList.add(new BasicNameValuePair("deviceID", "uniquedeviceid" + number));
        if (number % 2 == 0) {
            requestList.add(new BasicNameValuePair("language", LanguageType.Russian.toString()));
        } else {
            requestList.add(new BasicNameValuePair("language", LanguageType.English.toString()));
        }
        requestList.add(new BasicNameValuePair("deviceToken", getLongDevToken(number)));
        if (number % 2 == 0) {
            requestList.add(new BasicNameValuePair("mobilePlatform", MobilePlatform.IPhone.toString()));
        } else {
            requestList.add(new BasicNameValuePair("mobilePlatform", MobilePlatform.Android.toString()));
        }
        return requestList;
    }

    private static String getLongDevToken(int number) {
        return Md5Hash.getMd5Hash(String.valueOf(number));
    }

    private static class RegEntity implements Runnable {
        private int number;

        private RegEntity(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            try {
                ArrayList<NameValuePair> nameValuePairs = getParameters(number);
                HTTPRequestSender.sendPostRequest(nameValuePairs, url);
                counter--;
            } catch (IOException e) {
                loggerFactory.error(e);
            }
        }
    }

    public static boolean test() {
        try {
            int counter = 100;
            addManyUsers(counter);
            while (counter > 0) {
                Thread.sleep(500);
            }
            return (counter <= UserRequests.countUser());
        } catch (Exception e) {
            loggerFactory.error(e);
        }
        return false;
    }

}
