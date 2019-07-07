package com.example.hackathon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
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

import android.os.Handler;
import android.app.ProgressDialog;


public class Progress extends  FragmentActivity implements OnMapReadyCallback {


    // 구글 맵 참조변수 생성
    GoogleMap mMap;
    TextView textView;
    Location lastLocation;
    LatLng curLocation;
    private Handler mHandler;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);

        mHandler = new Handler();

        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                mProgressDialog = ProgressDialog.show(Progress.this,"",
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
        } );

        //startLocationService();
        // Current State 불러오는 부분
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)return;
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        curLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());

        // SupportMapFragment을 통해 레이아웃에 만든 fragment의 ID를 참조하고 구글맵을 호출한다.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); // getMapAsync must be called on the main thread.




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 구글 맵 객체를 불러온다.
        mMap = googleMap;

        // 서울에 대한 위치 설정

        LatLng seoul = new LatLng(37.52487, 126.92723);


        LatLng Busan = new LatLng(50.52487, 126.92723);

        // 구글 맵에 표시할 마커에 대한 옵션 설정(여러개 for문 처리)
        /*MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(seoul)
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));*/

        MarkerOptions makerOptions = new MarkerOptions();
        makerOptions
                .position(curLocation)
                .title("원하는 위치(위도, 경도)에 마커를 표시했습니다.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        // 마커를 생성한다.
        mMap.addMarker(makerOptions);

        //카메라를 서울 위치로 옮긴다.
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curLocation,15));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
    }
}