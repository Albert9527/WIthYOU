package com.zcz1024.withyou.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;

public class SetUpActivity extends BaseActivity {
    private TextView tv_acountinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        initView();
    }


    private void initView(){
        tv_acountinfo = findViewById(R.id.tv_setup_acount);

        tv_acountinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),AcountInfoActivity.class));
            }
        });
    }

    public void logout(View view){
        AcountInfo.seteditInfo(this,"userid",null);
        startActivity(new Intent(getBaseContext(),LoginActivity.class));
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
