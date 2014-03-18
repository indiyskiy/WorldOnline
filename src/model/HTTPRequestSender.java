package model;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTTPRequestSender {

    public static void sendPostRequest(List<NameValuePair> nvps, String urlString) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(urlString);
//        CloseableHttpResponse response1 = httpClient.execute(httpGet);
      /*  try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity1);
        } finally {
            response1.close();
        }*/

        HttpPost httpPost = new HttpPost(urlString);

        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response2 = httpClient.execute(httpPost);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
    }

    private static String getRequestString(HashMap<String, String> parameters) {
       /* if(parameters.isEmpty()){
            return "";
        }*/
        String result = "";
        for (Map.Entry pair : parameters.entrySet()) {
            if (!result.equals("")) {
                result += "&";
            }
            result += pair.getKey() + "=" + pair.getValue();
        }
        return result;
    }

    private static void disconnect(DataOutputStream writer, HttpURLConnection connection) {
        try {
            if (writer != null) {
                writer.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



/*    String urlParameters = getRequestString(parameters);
        URL url;
        HttpURLConnection connection = null;
        DataOutputStream writer = null;
        try {

            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod(sendingType.toString());
            connection.setRequestProperty("Content-Type", "text/html");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setUseCaches(false);

            writer = new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(urlParameters);
            writer.flush();

            System.out.println("send request " + urlParameters);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnect(writer, connection);
        }*/