package uz.aeroport.httpRequests;


import javafx.scene.control.TableView;
import uz.aeroport.models.TableData;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import uz.aeroport.utils.widgets.MyResourceBundle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;

/**
 * Created by Jack on 13.01.2019.
 */
public class HttpRequests
{
   private static URI url = URI.create("http://localhost:8080/departure/");
   public boolean departPost(JSONObject jsonObject)
   {
       CloseableHttpClient client =  HttpClientBuilder.create().build();
       HttpPost postRequest = new HttpPost(url);
       StringEntity se = null;
       Boolean responseServer = false;
       try
       {
           se = new StringEntity(jsonObject.toString(),"UTF-8");
           se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
           postRequest.setEntity(se);
           HttpResponse response = client.execute(postRequest);
           responseServer = (response != null) ? true : false;
       } catch (UnsupportedEncodingException e)
       {
           e.printStackTrace();
       } catch (ClientProtocolException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
        return responseServer;
   }
   public void getAll(TableView<TableData> tableShowD, MyResourceBundle myResourceBundle)
   {

     /*  try
       {
           URL url = new URL("http://localhost:8080/departure/");
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("GET");
           int responsecode = connection.getResponseCode();
           if(responsecode == HttpURLConnection.HTTP_OK)
           {
               BufferedReader in = new BufferedReader(new InputStreamReader(
                       connection.getInputStream()));
               String inputLine;
               StringBuffer response = new StringBuffer();

               while ((inputLine = in.readLine()) != null) {
                   response.append(inputLine);
               }
               in.close();

               // print result
               System.out.println(response.toString());
           }

       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
       System.out.println("OK");
       */
        HttpResponse response;
        CloseableHttpClient client =  HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
       try
       {
           response = client.execute(get);
           String jsonString = EntityUtils.toString(response.getEntity(),"UTF-8");
           JSONArray jsonArray = new JSONArray(jsonString);
           for(int i = 0 ; i < jsonArray.length();i ++)
           {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            TableData tableData = new TableData();
            tableData.setFlight(jsonObject.getString("flight"));
            tableData.setDestination(jsonObject.getString("destinationUzb"));
           // tableData.setStatus(jsonObject.getString("status"));
            if(jsonObject.getString("status").equals("schedule")){
                tableData.setStatus(myResourceBundle.getString("Status1"));
            }
            if(jsonObject.getString("status").equals("expected")){
                tableData.setStatus(myResourceBundle.getString("Status2"));
            }
            if(jsonObject.getString("status").equals("arrive")){
                tableData.setStatus(myResourceBundle.getString("Status3"));
            }
            if(jsonObject.getString("status").equals("cancel")){
                tableData.setStatus(myResourceBundle.getString("Status4"));
            }
            //label.setText(myresourcebundle.getString("birinchi"))
            tableData.setTime(jsonObject.getString("statusTime"));
            tableData.setTerminal(jsonObject.getString("terminal"));
            //tableData.setStatus(myResourceBundle.getString());
           /* if(jsonObject.getString("status") == null)
            {
                System.out.println("null bo`lganlari bu");
            }*/
            tableShowD.getItems().add(tableData);
          //  tableData.setStatus(jsonObject.getString("status"));
            //tableData.setTime(jsonObject.getString("departDate"));
           // tableData.setTerminal(jsonObject.getString("terminal"));
           // System.out.println(jsonObject.getString("flight"));
           }
        //   JSONObject jsonObject = new JSONObject(jsonString);
        //   System.out.println(jsonObject.get("flight"));
       } catch (IOException e) {
           e.printStackTrace();
       }

   }

}
