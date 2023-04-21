package com.gy25m.seoulfestival;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.kakao.util.maps.helper.Utility;

public class MainActivity extends AppCompatActivity {

    FragmentAdapter adapter;
    TabLayout tabLayout;
    ViewPager2 pager;
    TextView tv;
    String[] tabTitle=new String[]{"홈","콘서트","클래식","체험","전시회"};
    int[] tabIcon=new int[]{R.drawable.ic_action_home,R.drawable.ic_action_concert,R.drawable.ic_action_classic,R.drawable.ic_action_edu,R.drawable.ic_action_art};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager=findViewById(R.id.pager);
        tabLayout=findViewById(R.id.tab_layout);
        adapter=new FragmentAdapter(this);
        pager.setAdapter(adapter);
//        String keyHash= Utility.getKeyHash(this);
//        Log.i("keyHash",keyHash);
        TabLayoutMediator mediator=new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitle[position]);
                tab.setIcon(tabIcon[position]);

            }
        });
        mediator.attach();

        findViewById(R.id.tv).setOnClickListener(view -> {
            tabLayout.getTabAt(0).select();
        });


        int checkResult= ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS);
        if(checkResult== PackageManager.PERMISSION_DENIED){
            //알림 허용을 요청하는 다이얼로그를 보이기
            //requestPermissions(); //예전방식
            //퍼미션 요청결과를 받아주는 대행사 객체를 이용함.
            permissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            return;
        }

    }
    ActivityResultLauncher<String> permissionResultLauncher= registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if(result) Toast.makeText(MainActivity.this, "알림 허용", Toast.LENGTH_SHORT).show();
            else Toast.makeText(MainActivity.this, "알림을 보낼 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    });
}