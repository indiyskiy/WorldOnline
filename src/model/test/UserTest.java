package model.test;

import model.HTTPRequestSender;
import model.Md5Hash;
import model.constants.ServerConsts;
import model.constants.databaseenumeration.LanguageType;
import model.constants.databaseenumeration.MobilePlatform;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Илья on 14.03.14.
 */
public class UserTest {
    private static final String url = ServerConsts.ServerURL + ServerConsts.worldOnlineModule + ServerConsts.mobileModule + "userregistration";


    //marr registration request test
    public static void addManyUsers(int number) {
        for (int i = 0; i < number; i++) {
            Thread thread = new Thread(new RegEntity(i));
            thread.start();
        }
    }

    public static ArrayList<NameValuePair> getParameters(int number) {
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

    public static class RegEntity implements Runnable {
        private int number;

        public RegEntity(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            try {
                HTTPRequestSender.sendPostRequest(getParameters(number), url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        addManyUsers(100);
    }

}
