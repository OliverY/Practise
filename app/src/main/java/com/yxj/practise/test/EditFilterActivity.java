package com.yxj.practise.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;

import com.yxj.practise.R;

/**
 * Author:  Yxj
 * Time:    2018/7/19 下午5:49
 * -----------------------------------------
 * Description:
 */
public class EditFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_filter);

        EditText et = findViewById(R.id.et);

        et.setFilters(new InputFilter[]{filter});
    }

    InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            //防止过长
            Log.i("yxj","source::"+source+"  start::"+start+"  end::"+end+"  dest::"+dest+"  dstart::"+dstart+"  dend::"+dend);

            int keep = 13 - (dest.length() - (dend - dstart));
            if (keep <= 0) {
                return "";
            } else if (keep >= end - start) {
                //过滤wp
                if (source.equals(" ") || source.toString().contains("w") || source.toString().contains("W") || source.toString().contains("P") || source.toString().contains("p")) {
                    return "";
                } else {
                    return null;
                } // keep original
            } else {
                keep += start;
                if (Character.isHighSurrogate(source.charAt(keep - 1))) {
                    --keep;
                    if (keep == start) {
                        return "";
                    }
                }
                CharSequence result = source.subSequence(start, keep);
                return result;
            }

        }
    };
}
