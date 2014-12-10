package test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TestCardEventRegistration {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://148.251.42.117:8090/worldOnline/mobile/statistics/cardevent?userID=439");
//        StringEntity requestEntity =
//                new StringEntity("{\"cardEvents\":[{\"cardID\":3308,\"eventType\":1,\"eventTimestamp\":\"2015-01-05 22:00:00.0\"}]}");
        String json = "{\"cardEvents\":[";
        json += "{\"cardID\":3308,\"eventType\":1,\"eventTimestamp\":\"2015-01-05 22:00:00.0\",\"text\":\"simple text\"},";
        json += "{\"cardID\":3302,\"eventType\":1,\"eventTimestamp\":\"2015-01-05 22:00:00.0\",\"text\":\"simple text\"}]}";
        StringEntity requestEntity = new StringEntity(json);
        requestEntity.setContentType("application/json");
        httpPost.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(httpPost);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
    }
}
