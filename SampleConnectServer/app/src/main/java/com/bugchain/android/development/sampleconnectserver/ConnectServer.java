package com.bugchain.android.development.sampleconnectserver;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BUG CHAIN on 26/2/2558.
 */
public class ConnectServer {


    private String getContent(String url){
        String response = "";
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(new HttpGet(url));
            InputStream inputStream = httpResponse.getEntity().getContent();
            if(inputStream != null){
                response = convertInputStreamToString(inputStream);
            }
        }catch (ClientProtocolException e){
            Log.d("Connect Server","Can,t load content");
        }catch (IOException e){
            Log.d("Connect Server","Can,t load content");
        }
        return response;
    }

    private String postContent(String url,List<NameValuePair> nameValuePairs){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        BufferedReader bufferedReader = null;

        try{
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
            HttpResponse httpResponse = httpClient.execute(httpPost);

            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line + NL);
            }
            bufferedReader.close();
            return sb.toString();
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

    private String convertInputStreamToString(InputStream inputStream){
        String result = "";
        String line = "";

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"),8);
            while ((line = reader.readLine()) != null){
                result += line;
            }
            inputStream.close();
        }catch (UnsupportedEncodingException e){
            Log.d("Connect Server","Can't convert InputStream to string");
            e.printStackTrace();
        }catch (IOException e){
            Log.d("Connect Server","Can't convert InputStream to string");
            e.printStackTrace();
        }
        return  result;
    }


    public String createNewOne(String userName,String password,String firstName,
                               String lastName,String tel,String email){
        String url = "";
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("user_name",userName));
        nameValuePairs.add(new BasicNameValuePair("password",password));
        return postContent(url,nameValuePairs);
    }

    public String getMember(){
        String url = "http://softwaremark.com/android_project/sample_connect_server/getMember.php";
        return getContent(url);
    }
}
