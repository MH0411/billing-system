package controller;

import java.io.InputStream;
import model.ServerDetail;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;


public class Testing {

   public static void main(String[] args){
       
       String email = "z.mike0411@gmail.com";
       String subject = "receipt";
       String message = "message";
       try{
            JSONObject json = new JSONObject(); 

            json.put("email", email); 
            json.put("subject", subject);                                    
            json.put("message", message);
            System.out.println(json);

           HttpClient htppClient = new DefaultHttpClient();
           HttpPost request = new HttpPost("http://192.168.1.103/");
           StringEntity params = new StringEntity(json.toString());
           request.addHeader("content-type", "application/json");
           request.setEntity(params);
           HttpResponse response = htppClient.execute(request);
           
           if(response != null){
               InputStream in = response.getEntity().getContent();
           }
       } catch(Exception e){
           e.printStackTrace();
       }
   }
}