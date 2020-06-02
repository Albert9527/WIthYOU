package com.zcz1024.withyou.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.UserDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.entity.User;
import com.zcz1024.withyou.pojoVO.UserVo;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {
    private EditText et_username;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_pswd);
    }

    public void login(View view) {
        //登陆验证
        final String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String,String> acount = new HashMap<>();

        acount.put("username",username);
        acount.put("password",password);
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .LoginTest(acount)

                        //切换到IO线程执行网络请求
                        .subscribeOn(Schedulers.io())
                        //切换到UI线程执行UI操作
                        .observeOn(AndroidSchedulers.mainThread())
                        // 返回请求数据
                        .subscribe(new Consumer<ResultData<UserVo>>() {
                            @Override
                            public void accept(final ResultData<UserVo> resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    loginsuccese(resultData.getData().getUserId(),username);
                                    AcountInfo.setObject(getBaseContext(),"uinfo",resultData.getData());
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(new Intent(getBaseContext(),BasePageActivity.class));
                                            finish();
                                        }
                                    }, 1000);
                                } else if (!resultData.isSuccess()) {
                                    onfailue(resultData.getMsg());
                                }

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("errow", throwable.toString());
                            }
                        })
        );

}

/*    public void register(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }*/

    public void updatePswd(View view){
        startActivity(new Intent(this,UpdatePswdActivity.class));
    }

    public void onfailue(String msg) {
        Toast.makeText(LoginActivity.this, "登陆失败： "+msg, Toast.LENGTH_SHORT).show();
    }

    public void loginsuccese(String userid,String username) {
        AcountInfo.seteditInfo(this,"userid",userid);
        AcountInfo.seteditInfo(this,"acount",username);
        AcountInfo.seteditInfo(this,"oldcode","0");
        Toast.makeText(this, "用户 < "+username+" > 登陆成功",
                Toast.LENGTH_SHORT).show();
    }


    public void closeActivity(View view){
        startActivity(new Intent(getBaseContext(),BasePageActivity.class));
        finish();
    }

}
