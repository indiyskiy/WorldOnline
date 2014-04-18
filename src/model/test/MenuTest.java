package model.test;

import model.HTTPRequestSender;
import model.constants.Component;
import model.constants.ServerConsts;
import model.database.requests.UserRequests;
import model.database.worldonlinedb.UserEntity;
import model.logger.LoggerFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Илья on 14.04.14.
 */
public class MenuTest {
    private static final String url = ServerConsts.GlobalServerURL + ServerConsts.worldOnlineModule + ServerConsts.mobileModule + "userregistration";
    private static final LoggerFactory loggerFactory = new LoggerFactory(Component.Test, UserTest.class);

    public static void test() {
        try {
            ArrayList<UserEntity> userEntities = UserRequests.getAllUsers();
            Random random = new Random(System.currentTimeMillis());
            Long userID = userEntities.get(random.nextInt(userEntities.size())).getUserID();
            for (int i = 0; i < 100; i++) {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
                NameValuePair nameValuePair1 = new BasicNameValuePair("userID", String.valueOf(userID));
                NameValuePair nameValuePair2 = new BasicNameValuePair("menuID", String.valueOf(i));
                nameValuePairs.add(nameValuePair1);
                nameValuePairs.add(nameValuePair2);
                HTTPRequestSender.sendPostRequest(nameValuePairs, url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}