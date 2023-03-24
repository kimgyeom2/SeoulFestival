package com.gy25m.seoulfestival;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

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
        String keyHash= Utility.getKeyHash(this);
        Log.i("keyHash",keyHash);
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




    }
}