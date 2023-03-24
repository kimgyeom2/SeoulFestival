package com.gy25m.seoulfestival;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class HomeFragment extends Fragment {
    LinearLayout page1,page2,page3;
    Button btn1,btn2,btn3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        page1=view.findViewById(R.id.page1);
        page2=view.findViewById(R.id.page2);
        page3=view.findViewById(R.id.page3);

        view.findViewById(R.id.btn1).setOnClickListener(view1 -> {
            page1.setVisibility(View.VISIBLE);
            page2.setVisibility(View.GONE);
            page3.setVisibility(View.GONE);

        });
        view.findViewById(R.id.btn2).setOnClickListener(view2 -> {
            page1.setVisibility(View.GONE);
            page2.setVisibility(View.VISIBLE);
            page3.setVisibility(View.GONE);
        });
        view.findViewById(R.id.btn3).setOnClickListener(view3 -> {
            page1.setVisibility(View.GONE);
            page2.setVisibility(View.GONE);
            page3.setVisibility(View.VISIBLE);
        });
    }
}