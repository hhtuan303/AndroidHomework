package com.example.bai23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLParser {
    public String getXmlFromUrl(String url) {
        String xml = null;
        URL link = null;
        HttpURLConnection connection = null;
        try {
            link = new URL(url);
            connection = (HttpURLConnection) link.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            xml = sb.toString();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
