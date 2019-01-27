package uz.aeroport.httpRequests;


import javafx.scene.control.TableView;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import uz.aeroport.models.AirlinesList;
import uz.aeroport.models.TableData;
import uz.aeroport.utils.widgets.MyResourceBundle;
import uz.aeroport.utils.widgets.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 13.01.2019.
 */
public class HttpRequests
{
   private  URI url = URI.create("http://localhost:8080/");
   private CloseableHttpClient client;
   public boolean dataPost(JSONObject jsonObject, String temp)
   {
       url = URI.create(url.toString() + temp);
       this.client =  HttpClientBuilder.create().build();
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
   public boolean departPut(JSONObject jsonObject,String temp)
   {
       url = URI.create(url.toString() + temp);
       this.client = HttpClientBuilder.create().build();
       HttpPut put = new HttpPut(url);
       StringEntity stringEntity = null;
       boolean responseServer = false;
       try
       {
           stringEntity = new StringEntity(jsonObject.toString(),"UTF-8");
           stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
           put.setEntity(stringEntity);
           HttpResponse response = client.execute(put);
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
   public void getAll(TableView<TableData> tableShowA, TableView<TableData> tableShowD, MyResourceBundle myResourceBundle, String temp, LocalDate localDate)
   {

        url = URI.create(url.toString() + temp + "date=" + localDate.toString());
        HttpResponse response;
        this.client =  HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
       try
       {
           response = client.execute(get);
           String jsonString = EntityUtils.toString(response.getEntity(),"UTF-8");
           JSONArray jsonArray = new JSONArray(jsonString);
           if(temp.equals("departure/"))
           {
               tableShowD.getItems().clear();
           for(int i = 0 ; i < jsonArray.length();i ++)
           {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            TableData tableData = new TableData();
            tableData.setId((long) (i  + 1));
            tableData.setDataId(jsonObject.getLong("id"));
            tableData.setFlight(jsonObject.getString("flight"));
            tableData.setDestinationUzb(jsonObject.getString("destinationUzb"));
            tableData.setTime(jsonObject.getString("time"));
            tableData.setDestinationRus(jsonObject.getString("destinationRus"));
            tableData.setDestinationEng(jsonObject.getString("destinationEng"));
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
            tableData.setStatusTime(jsonObject.getString("statusTime"));
            tableData.setTerminal(jsonObject.getString("terminal"));
            tableData.setDepartDate(jsonObject.getString("departDate"));
            tableData.setAirlineId(jsonObject.getLong("airlineId"));
            tableShowD.getItems().add(tableData);
           }
           }
           else
           {
               System.out.println("arriveni ichinda");
               tableShowA.getItems().clear();
               for(int i = 0 ; i < jsonArray.length();i ++)
               {
                   JSONObject jsonObject = jsonArray.getJSONObject(i);
                   TableData tableData = new TableData();
                   tableData.setId((long) (i  + 1));
                   tableData.setDataId(jsonObject.getLong("id"));
                   tableData.setFlight(jsonObject.getString("flight"));
                   tableData.setDestinationUzb(jsonObject.getString("destinationUzb"));
                   tableData.setDestinationRus(jsonObject.getString("destinationRus"));
                   tableData.setDestinationEng(jsonObject.getString("destinationEng"));
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
                   tableData.setTime(jsonObject.getString("statusTime"));
                   tableData.setDepartDate(jsonObject.getString("arriveDate"));
                   tableData.setTime(jsonObject.getString("time"));
                   tableData.setStatusTime(jsonObject.getString("statusTime"));
                   tableData.setAirlineId(jsonObject.getLong("airlineId"));
                   tableShowA.getItems().add(tableData);
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
   public JSONObject getById(Long id,String temp)
   {
       url = URI.create(url.toString() + temp);
       JSONObject jsonObject = null;
       HttpResponse httpResponse;
       this.client =  HttpClientBuilder.create().build();
       HttpGet get = new HttpGet(url+"id="+id);
       try
       {
           httpResponse = client.execute(get);
           String jsonString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
           jsonObject = new JSONObject(jsonString);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return jsonObject;
   }
   public boolean checkLoginAndPassword(String login, String password) throws NoSuchAlgorithmException {
        JSONObject jsonObject = null;
        url = URI.create(url.toString() + "checker/");
        boolean check = true;
        HttpResponse httpResponse;
        this.client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
       try
       {
           httpResponse = client.execute(httpGet);
           String json = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
           jsonObject = new JSONObject(json);

       } catch (IOException e) {
           e.printStackTrace();
       }
       return  new Utils().checkLoginAndPassword(jsonObject,login,password);
   }

    public boolean checkOldPassword(String password) throws NoSuchAlgorithmException {
        JSONObject jsonObject = null;
        url = URI.create(url.toString() + "checker/");
        boolean check = true;
        HttpResponse httpResponse;
        this.client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        try
        {
            httpResponse = client.execute(httpGet);
            String json = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
            jsonObject = new JSONObject(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  new Utils().checkLoginOldPassword(jsonObject,password);
    }
    // parolni o`zgartirish
    public boolean changePassword(JSONObject jsonObject)
    {
        System.out.println(jsonObject);
        url = URI.create(url.toString()+"checker/");
        boolean change = false;

        HttpPost post = new HttpPost(url);
        this.client =  HttpClientBuilder.create().build();
        StringEntity stringEntity = new StringEntity(jsonObject.toString(),"UTF-8");
        stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
        post.setEntity(stringEntity);
        try
        {
            HttpResponse  response = client.execute(post);
            change = (response != null) ? true : false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return change;
    }
    public boolean postImage(JSONObject jsonObject)
    {
        boolean answer = false;
        url = URI.create(url.toString()+"airlines/");
        this.client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(jsonObject.toString(),"UTF-8");
        stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
        post.setEntity(stringEntity);
        try
        {
            HttpResponse response = client.execute(post);
            answer = (response != null) ? true : false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
    public List<AirlinesList> getAllAirLines()
    {
        List<AirlinesList> lists = new ArrayList<>();
        url = URI.create(url.toString()+"airlines/");
        this.client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        HttpResponse response;
        try
        {
            response = client.execute(get);
            String entity =  EntityUtils.toString(response.getEntity(),"UTF-8");
            JSONArray jsonArray = new JSONArray(entity);
            lists =  new Utils().getFromJson(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;

    }


}
