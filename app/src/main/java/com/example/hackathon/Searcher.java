package com.example.hackathon;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Searcher {
    private final String CLIENT_ID = "gNb6sSonzRoMMVx1lJ36";
    private final String CLIENT_SECRET = "acxi_ZN8jg";

    public void search(final String location, final Handler handler) throws IOException {
        new Thread() {
            public void run() {
                try {
                    String jsonString = getSearchData(location);
                    Bundle bun = new Bundle();
                    String result = parseData(jsonString);

                    bun.putString("SEARCH_DATA", result);

                    Message msg = handler.obtainMessage();
                    msg.setData(bun);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String getSearchData(String location) throws IOException {
        String searchData = "";
        String query = URLEncoder.encode(location + " 맛집", "UTF-8");
        URL url = new URL(
                "https://openapi.naver.com/v1/search/local.json?query=" + query + "&display=30");

        HttpURLConnection urlConnection = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
            urlConnection.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);

            int status = urlConnection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                isr = new InputStreamReader(urlConnection.getErrorStream());
            } else {
                isr = new InputStreamReader(urlConnection.getInputStream());
            }
            br = new BufferedReader(isr);

            String str = null;
            while ((str = br.readLine()) != null) {
                searchData += str + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (isr != null) {
                isr.close();
            }
            if (br != null) {
                br.close();
            }
        }

        return searchData;
    }

    private String parseData(String jsonString) throws JSONException {
        JSONObject jsonObj = new JSONObject(jsonString);
        JSONArray items = jsonObj.getJSONArray("items");
        String returnValue = "";
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String title = (String) item.get("title");

            returnValue += title + "\n";
        }

        Log.i("", returnValue);
        return returnValue;
    }
}