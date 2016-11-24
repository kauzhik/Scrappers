/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.scrapper.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author kaushik
 */
public class Grabber {
    
    public static HttpURLConnection getConnection(String link) throws IOException{
        URL url = new URL(link);
        return (HttpURLConnection) url.openConnection();
    }
    
    public static String getContent(InputStream iStream) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
        String line = "";
        StringBuilder builder = new StringBuilder();
        while((line = reader.readLine()) != null){
            builder.append(line);
        }
        reader.close();
        return builder.toString();
    }
    
    public static String post(String link, String params) throws IOException{
        HttpURLConnection conn = getConnection(link);
//            Set to post method
        conn.setDoOutput(true);
//            set a keyword
        String queryStr = params;
//            write it down to get the desired page of java search through post method
        OutputStream os = conn.getOutputStream();
        os.write(queryStr.getBytes());

//            Now you want to read and filter
        return getContent(conn.getInputStream());
        
    }
    
    public static String get(String link) throws IOException{
        HttpURLConnection conn = getConnection(link);
        return getContent(conn.getInputStream());
    }
    
}
