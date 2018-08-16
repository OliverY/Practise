package com.yxj.practise.test.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxj.practise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Yxj
 * Time:    2018/8/16 上午10:42
 * -----------------------------------------
 * Description:
 */
public class FirstFragment extends Fragment {

    public String title;
    public View root;

    public static ParentFragment newInstance(String title){
        ParentFragment fragment = new ParentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_first,container,false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String title = getArguments().getString("title");
        TextView tv = root.findViewById(R.id.tv_title);
        tv.setText(title);

        ViewPager vp = root.findViewById(R.id.vp_child);

        final List<Fragment> fragmentList = new ArrayList<>();
        for(int i=1;i<4;i++){
            fragmentList.add(ParentFragment.newInstance("子"+i));
        }

        FragmentManager fm = getFragmentManager();
        FragmentStatePagerAdapter fsa = new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        vp.setAdapter(fsa);

    }
}
