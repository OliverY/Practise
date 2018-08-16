package com.yxj.practise.test.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yxj.baselib.Constant;
import com.yxj.practise.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:  Yxj
 * Time:    2018/8/16 上午10:35
 * -----------------------------------------
 * Description:
 */

@Route(path = Constant.PATH_VIEW_PAGER)
public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        vp = findViewById(R.id.vp);

        init();
    }

    private void init() {
        final List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(FirstFragment.newInstance("父0"));
        for(int i=1;i<4;i++){
            fragmentList.add(FirstFragment.newInstance("父"+i));
        }

        FragmentManager fm = getSupportFragmentManager();
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
