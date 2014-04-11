package model;

import model.constants.Component;
import model.logger.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class HTTPRequestSender {
    private static LoggerFactory loggerFactory = new LoggerFactory(Component.Global, HTTPRequestSender.class);

    public static void sendPostRequest(List<NameValuePair> nameValuePairs, String urlString) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(urlString);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        CloseableHttpResponse response2 = httpClient.execute(httpPost);
        try {
            HttpEntity entity2 = response2.getEntity();
            EntityUtils.consume(entity2);
        } catch (Exception e) {
            loggerFactory.error(e);
        } finally {
            response2.close();
        }
    }

}
