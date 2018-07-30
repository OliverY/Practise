package com.yxj.moduleb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yxj.baselib.Constant;

@Route(path = Constant.PATH_MODULEB_B)
public class ModuleBActivity extends AppCompatActivity {

    @Autowired
    public String name;

    @Autowired
    public int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        name = getIntent().getStringExtra("name");
//        age = getIntent().getIntExtra("age",-1);

        ARouter.getInstance().inject(this);

        Toast.makeText(this,name+" "+age,Toast.LENGTH_SHORT).show();
    }
}
