package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends  FragmentActivity implements OnMapReadyCallback {


    // 구글 맵 참조변수 생성
    GoogleMap mMap;
    TextView textView;
    Location lastLocation;
    LatLng curLocation;

    private Handler mHandler;
    private ProgressDialog mProgressDialog;


    private JSONArray mArray;
    private String data;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent fin = getIntent();
        String[] category = fin.getStringArrayExtra("strings");
        //startLocationService();
        // Current State 불러오는 부분
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)return;
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        curLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());

        // SupportMapFragment을 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출한다.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); // getMapAsync must be called on the main thread.

      /*  Searcher searcher = new Searcher();

         handler = new Handler() {
            public void handleMessage(Message msg) {
                Bundle bun = msg.getData();
                data = bun.getString("SEARCH_DATA");
                try {
                    mArray = new JSONArray(data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        try {
            searcher.search(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


      /*  try {
            mArray = new JSONArray(data);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

        /*mHandler = new Handler();

        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mProgressDialog = ProgressDialog.show(MainActivity.this,"",
                        "잠시만 기다려 주세요.",true);
                mHandler.postDelayed( new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                                mProgressDialog.dismiss();
                            }
                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                }, 3000);
            }
        } );*/

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 구글 맵 객체를 불러온다.
        mMap = googleMap;

        // 서울에 대한 위치 설정
// for loop를 통한 n개의 마커 생성
       /* data = "[{\"title\":\"한일관 광화문점\",\"category\":\"한식>한정식\",\"lat\":\"37.5748167\",\"lng\":\"126.9793678\",\"priority\":1},{\"title\":\"명동칼국수\",\"category\":\"음식점>한식\",\"lat\":\"37.5754803\",\"lng\":\"126.9790078\",\"priority\":1},{\"title\":\"카페마마스 광화문점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5747241\",\"lng\":\"126.979086\",\"priority\":2},{\"title\":\"도쿄등심 광화문점\",\"category\":\"음식점>한식>육류,고기요리>소고기구이\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"테라로사 광화문점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5748007\",\"lng\":\"126.9796057\",\"priority\":2},{\"title\":\"진가와 광화문점\",\"category\":\"음식점>일식>일식당\",\"lat\":\"37.5747869\",\"lng\":\"126.9790512\",\"priority\":2},{\"title\":\"우드앤브릭 더케이트윈타워점\",\"category\":\"카페,디저트>베이커리\",\"lat\":\"37.5746653\",\"lng\":\"126.9795849\",\"priority\":2},{\"title\":\"버거그루72\",\"category\":\"음식점>햄버거\",\"lat\":\"37.5748502\",\"lng\":\"126.9790843\",\"priority\":2},{\"title\":\"생어거스틴 경복궁점\",\"category\":\"음식점>아시아음식\",\"lat\":\"37.5748502\",\"lng\":\"126.9790843\",\"priority\":2},{\"title\":\"폴바셋 광화문점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5747769\",\"lng\":\"126.9789381\",\"priority\":2},{\"title\":\"델리커리 광화문점\",\"category\":\"음식점>인도음식\",\"lat\":\"37.5748502\",\"lng\":\"126.9790843\",\"priority\":2},{\"title\":\"키친토크 더케이트윈타워점\",\"category\":\"한식>백반,가정식\",\"lat\":\"37.5747343\",\"lng\":\"126.9792217\",\"priority\":2},{\"title\":\"스타벅스 경복궁사거리점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5756763\",\"lng\":\"126.9808391\",\"priority\":2},{\"title\":\"더하루 트윈트리점\",\"category\":\"음식점>푸드코트\",\"lat\":\"37.5756318\",\"lng\":\"126.9798548\",\"priority\":2},{\"title\":\"전주밥차\",\"category\":\"음식점>한식뷔페\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"크레이지후라이 광화문점\",\"category\":\"분식>떡볶이\",\"lat\":\"37.5748502\",\"lng\":\"126.9790843\",\"priority\":2},{\"title\":\"봉추찜닭 광화문점\",\"category\":\"한식>찜닭\",\"lat\":\"37.5748865\",\"lng\":\"126.9791065\",\"priority\":2},{\"title\":\"투썸플레이스 경복궁점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"이디야커피 광화문트윈트리타워점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"부산아지매국밥 트윈트리타워점\",\"category\":\"한식>국밥\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"리틀하노이 광화문점\",\"category\":\"음식점>베트남음식\",\"lat\":\"37.5748242\",\"lng\":\"126.9791979\",\"priority\":2},{\"title\":\"샌드프레소 광화문점\",\"category\":\"음식점>샌드위치\",\"lat\":\"37.5755056\",\"lng\":\"126.9788037\",\"priority\":2},{\"title\":\"단물곤물 광화문점\",\"category\":\"육류,고기요리>닭요리\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"샐러드까페 하루06\",\"category\":\"음식점>카페,디저트\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"이츠크리스피\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"산촌분식\",\"category\":\"분식>종합분식\",\"lat\":\"37.5754889\",\"lng\":\"126.9789624\",\"priority\":2},{\"title\":\"발재반점 경복궁점\",\"category\":\"중식>중식당\",\"lat\":\"37.57476\",\"lng\":\"126.9790629\",\"priority\":3},{\"title\":\"타누키돈부리 광화문트원트리점\",\"category\":\"일식>일식당\",\"lat\":\"37.5755117\",\"lng\":\"126.9805583\",\"priority\":3},{\"title\":\"가츠라 경복궁점\",\"category\":\"일식>일식당\",\"lat\":\"37.5747077\",\"lng\":\"126.9792674\",\"priority\":3},{\"title\":\"차이나보다\",\"category\":\"중식>중식당\",\"lat\":\"37.5754868\",\"lng\":\"126.9797549\",\"priority\":3}]";

        try {
            mArray = new JSONArray(data);


        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        /*try {
            mArray = new JSONArray(data);

            for(int i =0;i<15;i++)
            {

                JSONObject jsonObject = null;
                String lat = null;
                String title = null;
                String lng = null;
                int color_num = 0;

                try {

                    jsonObject = mArray.getJSONObject(i);
                    title = jsonObject.getString("title");
                    lat = jsonObject.getString("lat");
                    lng = jsonObject.getString("lng");
                    color_num = jsonObject.getInt("priority");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                double nlatitude = Double.parseDouble(lng);
                double nlongitude = Double.parseDouble(lat);
                //LatLng nStore =new LatLng(nlatitude, nlongitude);
                //현재 검색, 1 파랑, 2 초록, 3 빨강
                MarkerOptions makerOptions = new MarkerOptions();
                makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                        .position(new LatLng(37.52487 + idx, 126.92723))
                        .title("마커" + idx); // 타이틀.

                // 2. 마커 생성 (마커를 나타냄)
                mMap.addMarker(makerOptions);
                mMap.addMarker(mArray_Marker);
            }
                else if(color_num == 2){
                mArray_Marker
                        .position(nStore)
                        .title(title)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                System.out.println("data" + nlatitude);
                mMap.addMarker(mArray_Marker);
            }
            else {
                mArray_Marker
                        .position(nStore)
                        .title(title)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                System.out.println("data" + nlatitude);
                mMap.addMarker(mArray_Marker);
            }

        }


    } catch (JSONException e) {
        e.printStackTrace();
    }*/

       /* for (int idx = 0; idx < 10; idx++) {
            // 1. 마커 옵션 설정 (만드는 과정)
            MarkerOptions makerOptions = new MarkerOptions();
            makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                    .position(new LatLng(37.52487 + idx, 126.92723))
                    .title("마커" + idx); // 타이틀.

            // 2. 마커 생성 (마커를 나타냄)
            mMap.addMarker(makerOptions);
        }*/

        // 카메라를 위치로 옮긴다.
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.52487, 126.92723)));
        /*LatLng seoul = new LatLng(37.52487, 126.92723);


        LatLng Busan = new LatLng(50.52487, 126.92723);

        // 구글 맵에 표시할 마커에 대한 옵션 설정(여러개 for문 처리)
        *//*MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(seoul)
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));*//*

        MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(curLocation)
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        // 마커를 생성한다.
        mMap.addMarker(makerOptions);

       makerOptions = new MarkerOptions();
       makerOptions
                .position(new LatLng(37.52487, 126.92723))
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        // 마커를 생성한다.
        mMap.addMarker(makerOptions);

        makerOptions = new MarkerOptions();
        makerOptions
                .position(new LatLng(37.52487, 126.92723))
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        // 마커를 생성한다.
        mMap.addMarker(makerOptions);
        makerOptions = new MarkerOptions();
        makerOptions
                .position(new LatLng(24.52487, 120.92723))
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        // 마커를 생성한다.
        mMap.addMarker(makerOptions);
        makerOptions = new MarkerOptions();
        makerOptions
                .position(new LatLng(38.52487, 123.92723))
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        // 마커를 생성한다.
        mMap.addMarker(makerOptions);

        *//*for (int idx = 0; idx < 10; idx++) {
            // 1. 마커 옵션 설정 (만드는 과정)
            MarkerOptions makerOptions = new MarkerOptions();
            makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                    .position(new LatLng(37.52487 + idx, 126.92723))
                    .title("마커" + idx)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));// 타이틀.

            // 2. 마커 생성 (마커를 나타냄)
            mMap.addMarker(makerOptions);
        }*//*
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.52487, 126.92723)));
        //카메라를 서울 위치로 옮긴다.
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
        //Searcher searcher = new Searcher();
        //Log.d("chk1","chk1");
      *//*  data = "[{\"title\":\"한일관 광화문점\",\"category\":\"한식>한정식\",\"lat\":\"37.5748167\",\"lng\":\"126.9793678\",\"priority\":1},{\"title\":\"명동칼국수\",\"category\":\"음식점>한식\",\"lat\":\"37.5754803\",\"lng\":\"126.9790078\",\"priority\":1},{\"title\":\"카페마마스 광화문점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5747241\",\"lng\":\"126.979086\",\"priority\":2},{\"title\":\"도쿄등심 광화문점\",\"category\":\"음식점>한식>육류,고기요리>소고기구이\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"테라로사 광화문점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5748007\",\"lng\":\"126.9796057\",\"priority\":2},{\"title\":\"진가와 광화문점\",\"category\":\"음식점>일식>일식당\",\"lat\":\"37.5747869\",\"lng\":\"126.9790512\",\"priority\":2},{\"title\":\"우드앤브릭 더케이트윈타워점\",\"category\":\"카페,디저트>베이커리\",\"lat\":\"37.5746653\",\"lng\":\"126.9795849\",\"priority\":2},{\"title\":\"버거그루72\",\"category\":\"음식점>햄버거\",\"lat\":\"37.5748502\",\"lng\":\"126.9790843\",\"priority\":2},{\"title\":\"생어거스틴 경복궁점\",\"category\":\"음식점>아시아음식\",\"lat\":\"37.5748502\",\"lng\":\"126.9790843\",\"priority\":2},{\"title\":\"폴바셋 광화문점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5747769\",\"lng\":\"126.9789381\",\"priority\":2},{\"title\":\"델리커리 광화문점\",\"category\":\"음식점>인도음식\",\"lat\":\"37.5748502\",\"lng\":\"126.9790843\",\"priority\":2},{\"title\":\"키친토크 더케이트윈타워점\",\"category\":\"한식>백반,가정식\",\"lat\":\"37.5747343\",\"lng\":\"126.9792217\",\"priority\":2},{\"title\":\"스타벅스 경복궁사거리점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5756763\",\"lng\":\"126.9808391\",\"priority\":2},{\"title\":\"더하루 트윈트리점\",\"category\":\"음식점>푸드코트\",\"lat\":\"37.5756318\",\"lng\":\"126.9798548\",\"priority\":2},{\"title\":\"전주밥차\",\"category\":\"음식점>한식뷔페\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"크레이지후라이 광화문점\",\"category\":\"분식>떡볶이\",\"lat\":\"37.5748502\",\"lng\":\"126.9790843\",\"priority\":2},{\"title\":\"봉추찜닭 광화문점\",\"category\":\"한식>찜닭\",\"lat\":\"37.5748865\",\"lng\":\"126.9791065\",\"priority\":2},{\"title\":\"투썸플레이스 경복궁점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"이디야커피 광화문트윈트리타워점\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"부산아지매국밥 트윈트리타워점\",\"category\":\"한식>국밥\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"리틀하노이 광화문점\",\"category\":\"음식점>베트남음식\",\"lat\":\"37.5748242\",\"lng\":\"126.9791979\",\"priority\":2},{\"title\":\"샌드프레소 광화문점\",\"category\":\"음식점>샌드위치\",\"lat\":\"37.5755056\",\"lng\":\"126.9788037\",\"priority\":2},{\"title\":\"단물곤물 광화문점\",\"category\":\"육류,고기요리>닭요리\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"샐러드까페 하루06\",\"category\":\"음식점>카페,디저트\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"이츠크리스피\",\"category\":\"카페,디저트>카페\",\"lat\":\"37.5757051\",\"lng\":\"126.980001\",\"priority\":2},{\"title\":\"산촌분식\",\"category\":\"분식>종합분식\",\"lat\":\"37.5754889\",\"lng\":\"126.9789624\",\"priority\":2},{\"title\":\"발재반점 경복궁점\",\"category\":\"중식>중식당\",\"lat\":\"37.57476\",\"lng\":\"126.9790629\",\"priority\":3},{\"title\":\"타누키돈부리 광화문트원트리점\",\"category\":\"일식>일식당\",\"lat\":\"37.5755117\",\"lng\":\"126.9805583\",\"priority\":3},{\"title\":\"가츠라 경복궁점\",\"category\":\"일식>일식당\",\"lat\":\"37.5747077\",\"lng\":\"126.9792674\",\"priority\":3},{\"title\":\"차이나보다\",\"category\":\"중식>중식당\",\"lat\":\"37.5754868\",\"lng\":\"126.9797549\",\"priority\":3}]";
        try {
            mArray = new JSONArray(data);

            for(int i =0;i<15;i++)
            {

                JSONObject jsonObject = null;
                String lat = null;
                String title = null;
                String lng = null;
                int color_num = 0;

                try {

                    jsonObject = mArray.getJSONObject(i);
                    title = jsonObject.getString("title");
                    lat = jsonObject.getString("lat");
                    lng = jsonObject.getString("lng");
                    color_num = jsonObject.getInt("priority");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                double nlatitude = Double.parseDouble(lng);
                double nlongitude = Double.parseDouble(lat);
                //LatLng nStore =new LatLng(nlatitude, nlongitude);
                //현재 검색, 1 파랑, 2 초록, 3 빨강
                MarkerOptions mArray_Marker = new MarkerOptions();
                mArray_Marker
                        .position(new LatLng(nlatitude,nlongitude))
                        .title(title);
                        *//**//*.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));*//**//*

                Log.d("title",title);
                Log.d("nlatitude","n"+nlatitude);
                Log.d("nlongitude","n"+nlongitude);
                mMap.addMarker(mArray_Marker);
                *//**//*if(color_num == 1){
                    mArray_Marker
                            .position(nStore)
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                    System.out.println("data" + nlatitude);
                    mMap.addMarker(mArray_Marker);
                }
                else if(color_num == 2){
                    mArray_Marker
                            .position(nStore)
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    System.out.println("data" + nlatitude);
                    mMap.addMarker(mArray_Marker);
                }
                else {
                    mArray_Marker
                            .position(nStore)
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    System.out.println("data" + nlatitude);
                    mMap.addMarker(mArray_Marker);
                }*//**//*

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        *//*
        *//*handler = new Handler() {

            public void handleMessage(Message msg) {
               Log.d("chk","chk");
                Bundle bun = msg.getData();
                data = bun.getString("SEARCH_DATA");
                try {
                    mArray = new JSONArray("data"+data);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i =0;i<30;i++)
                {
                    MarkerOptions mArray_Marker = new MarkerOptions();
                    JSONObject jsonObject = null;
                    String lat = null;
                    String title = null;
                    String lng = null;
                    int color_num = 0;

                    try {

                        jsonObject = mArray.getJSONObject(i);
                        title = jsonObject.getString("title");
                        lat = jsonObject.getString("lat");
                        lng = jsonObject.getString("lng");
                        color_num = jsonObject.getInt("priority");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    double nlatitude = Double.parseDouble(lng);
                    double nlongitude = Double.parseDouble(lat);
                    System.out.println(nlatitude);
                   *//**//* LatLng nStore =new LatLng(nlatitude, nlongitude);
                    //현재 검색, 1 파랑, 2 초록, 3 빨강
                    if(color_num == 1){
                        mArray_Marker
                                .position(nStore)
                                .title(title)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                    }
                    else if(color_num == 2){
                        mArray_Marker
                                .position(nStore)
                                .title(title)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                    }
                    else {
                        mArray_Marker
                                .position(nStore)
                                .title(title)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }
                    mMap.addMarker(mArray_Marker);*//**//*
                }
            }



        };

        try {
            searcher.search(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }*//*
        *//*handler = new Handler() {
            public void handleMessage(Message msg) {
                for(int i =0;i<30;i++)
                {
                    MarkerOptions mArray_Marker = new MarkerOptions();
                    JSONObject jsonObject = null;
                    String title = null;
                    String lat = null;
                    String lng = null;
                    int color_num = 0;

                    try {

                        jsonObject = mArray.getJSONObject(i);
                        title = jsonObject.getString("title");
                        lat = jsonObject.getString("lat");
                        lng = jsonObject.getString("lng");
                        color_num = jsonObject.getInt("priority");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    double nlatitude = Double.parseDouble(lng);
                    double nlongitude = Double.parseDouble(lat);

                    LatLng nStore =new LatLng(nlatitude, nlongitude);
                    //현재 검색, 1 파랑, 2 초록, 3 빨강
                    if(color_num == 1){
                        mArray_Marker
                                .position(nStore)
                                .title(title)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                    }
                    else if(color_num == 2){
                        mArray_Marker
                                .position(nStore)
                                .title(title)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

                    }
                    else {
                        mArray_Marker
                                .position(nStore)
                                .title(title)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    }

                }
            }
        };*//*

        *//*for(int i =0;i<30;i++)
        {
            MarkerOptions mArray_Marker = new MarkerOptions();
            JSONObject jsonObject = null;
            String title = null;
            String lat = null;
            String lng = null;
            int color_num = 0;

            try {

                jsonObject = mArray.getJSONObject(i);
                title = jsonObject.getString("title");
                lat = jsonObject.getString("lat");
                lng = jsonObject.getString("lng");
                color_num = jsonObject.getInt("priority");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            double nlatitude = Double.parseDouble(lng);
            double nlongitude = Double.parseDouble(lat);

            LatLng nStore =new LatLng(nlatitude, nlongitude);
            //현재 검색, 1 파랑, 2 초록, 3 빨강
            if(color_num == 1){
                mArray_Marker
                        .position(nStore)
                        .title(title)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

            }
            else if(color_num == 2){
                mArray_Marker
                        .position(nStore)
                        .title(title)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

            }
            else {
                mArray_Marker
                        .position(nStore)
                        .title(title)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            }

        }*//*
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLocation,15));
*/
    }
}
