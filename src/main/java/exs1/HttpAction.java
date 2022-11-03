package exs1;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpAction {

    public String post(String url,String json)
    {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()){
            //Post request
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("content-type", "application/json");
            System.out.println("Executing POST request...");

            httpPost.setEntity(new StringEntity(json));

            //Execute the request
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                StatusLine statusLine = response.getStatusLine();
                System.out.println("Status code: "+statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return (EntityUtils.toString(entity));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Error";
    }
    public String get(String uri) throws IOException {

        //Creating a HttpClient object
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            final HttpGet request = new HttpGet(uri); //Creating a HttpGet object

            System.out.println("\nExecuting GET request...");
            try (CloseableHttpResponse response = httpclient.execute(request)) {
                StatusLine statusLine = response.getStatusLine();
                System.out.println("Status code: "+statusLine.getStatusCode() + " " + statusLine.getReasonPhrase());

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return (EntityUtils.toString(entity));
                }
            }catch (ClientProtocolException e){return ("URI doesn't found");}
        }
        return "Error";
    }

    public void delete(String url)
    {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(url);

            System.out.println("Executing DELETE request...");
            HttpResponse response = httpclient.execute(httpDelete);

            System.out.println("Status code: " + response.getStatusLine().getStatusCode());

            String responseBody = new BasicResponseHandler().handleResponse(response);

            System.out.println("Response: " + responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Using PUT to replace an existing Resource entirely
    public String put(String url,String json)
    {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPut httpPut = new HttpPut(url);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("content-type", "application/json");

            // specify the PUT body to send to the server as part of the request
            httpPut.setEntity(new StringEntity(json));

            System.out.println("\nExecuting PUT request...");
            try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
                int status= response.getStatusLine().getStatusCode();

                //Throw runtime exception if status code isn't 200
                if (status!= 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
                }
                System.out.println("Status code:" + status);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return(EntityUtils.toString(entity));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "error";
    }

    //Using PATCH to update partially
    public String patch(String url,String json) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpPatch httpPut = new HttpPatch(url);
            httpPut.setHeader("Accept", "application/json");
            httpPut.setHeader("content-type", "application/json");

            httpPut.setEntity(new StringEntity(json));

            System.out.println("\nExecuting PATCH request...");
            try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
                System.out.println("Status code:" + response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return (EntityUtils.toString(entity));
                }
            }
        }
        return "error";
    }
}


