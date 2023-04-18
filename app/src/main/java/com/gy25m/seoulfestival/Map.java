package com.gy25m.seoulfestival;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        MapView mapView=new MapView(this);
        RelativeLayout re=findViewById(R.id.container);
        re.addView(mapView);

        //TpkkoSearchApp PlaceMapFragment 참고


    }
}