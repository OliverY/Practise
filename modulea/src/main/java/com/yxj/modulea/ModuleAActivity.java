package com.yxj.modulea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yxj.baselib.Constant;

@Route(path = Constant.PATH_MODULEA_A)
public class ModuleAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulea_a);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getInstance().build(Constant.PATH_RA).navigation();
                ARouter.getInstance()
                        .build(Constant.PATH_MODULEB_B)
                        .withString("name","yxj")
                        .withInt("age",20)
                        .navigation(ModuleAActivity.this, new NavigationCallback(){
                            @Override
                            public void onFound(Postcard postcard) {
                                Log.e("yxj","onFound::"+postcard);
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                Log.e("yxj","onLost::"+postcard);
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.e("yxj","onArrival::"+postcard);
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Log.e("yxj","onInterrupt::"+postcard);
                            }
                        });
            }
        });


    }
}