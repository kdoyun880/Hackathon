package com.example.hackathon;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Searcher {
    public void search(final Handler handler) throws IOException {
        new Thread() {
            public void run() {
                try {
                    String jsonString = getSearchData();
                    Bundle bun = new Bundle();

                    bun.putString("SEARCH_DATA", jsonString);

                    Message msg = handler.obtainMessage();
                    msg.setData(bun);
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String getSearchData() throws IOException {
        String searchData = "";
        URL url = new URL(
                "https://git-hackathon-hippo.herokuapp.com/search");

        HttpURLConnection urlConnection = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");

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
}