package com.gy25m.seoulfestival;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    //프래그먼트 참조변수 3개짜리 배열객체
    Fragment[] fragments=new Fragment[5];
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        fragments[0]=new HomeFragment();
        fragments[1]=new ConcertFragment();
        fragments[2]=new ClassicFragment();
        fragments[3]=new EduFragment();
        fragments[4]=new ArtFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
