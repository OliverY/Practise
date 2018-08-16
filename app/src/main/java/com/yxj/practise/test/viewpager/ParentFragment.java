package com.yxj.practise.test.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxj.practise.R;

/**
 * Author:  Yxj
 * Time:    2018/8/16 上午10:42
 * -----------------------------------------
 * Description:
 */
public class ParentFragment extends Fragment {

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
        root = inflater.inflate(R.layout.fragment_parent,container,false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String title = getArguments().getString("title");
        TextView tv = root.findViewById(R.id.tv_title);
        tv.setText(title);

    }
}
