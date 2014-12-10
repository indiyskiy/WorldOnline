package test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TestCardActivityRegistration {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://148.251.42.117:8090/worldOnline/mobile/statistics/cardactivity?userID=439");
        StringEntity requestEntity =
                new StringEntity("{\"cardActivities\":[{\"cardID\":3308,\"onTimestamp\":\"2015-01-05 19:30:00.0\",\"offTimestamp\":\"2015-01-05 22:00:00.0\"},{\"cardID\":3320,\"onTimestamp\":\"2015-01-05 19:30:00.0\",\"offTimestamp\":\"2015-01-05 22:00:00.0\"}]}");
        requestEntity.setContentType("application/json");
        httpPost.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(httpPost);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
    }
}
