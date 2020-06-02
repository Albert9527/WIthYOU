package com.zcz1024.withyou.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.liji.circleimageview.CircleImageView;
import com.zcz1024.withyou.Presenter.NetWorkData.AppConfig;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.UserDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.Util.BitMapUtil;
import com.zcz1024.withyou.Util.UserPortraitDialog;
import com.zcz1024.withyou.entity.User;
import com.zcz1024.withyou.pojoVO.UserVo;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AcountInfoActivity extends BaseActivity {

    private TextView tv_nickname, tv_intro, tv_birthday, tv_sex;
    private CircleImageView img_avatar;
    private String userid;
    private UserPortraitDialog dialog;
    private MultipartBody.Part body;
    private LinearLayout layout_avatar, layout_nickname, layout_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_info);
        initView();
        getUserinfo();
    }

    private void initView() {
        userid = AcountInfo.geteditInfo(this, "userid");

        if (userid == null) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        tv_nickname = findViewById(R.id.tv_uinfo_nickname);
        tv_intro = findViewById(R.id.tv_uinfo_intro);
        tv_birthday = findViewById(R.id.tv_uinfo_birthday);
        tv_sex = findViewById(R.id.tv_uinfo_sex);
        img_avatar = findViewById(R.id.img_uinfo_avatar);

        layout_avatar = findViewById(R.id.layout_uinfo_avatar);
        layout_nickname = findViewById(R.id.layout_uinfo_nickname);
        layout_intro = findViewById(R.id.layout_uinfo_intro);

    }

    public void updateLisener(View view){
        switch (view.getId()){
            case R.id.layout_uinfo_avatar:
                showPortraitDialog();
                break;
            case R.id.layout_uinfo_nickname:
                updateUinfo("nickname",tv_nickname.getText().toString());
                break;
            case R.id.layout_uinfo_intro:
                updateUinfo("intro",tv_intro.getText().toString());
                break;
        }
    }

    private void getUserinfo() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .getUserInfo(userid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<UserVo>>() {
                            @Override
                            public void accept(ResultData<UserVo> userVoResultData) throws Exception {
                                if (userVoResultData.isSuccess()) {
                                    setData(userVoResultData.getData());
                                } else {
                                    Toast.makeText(getBaseContext(), userVoResultData.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

    private void updateUinfo(String cloumName, String newInfo) {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .updateInfo(userid, cloumName, newInfo)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData>() {
                            @Override
                            public void accept(ResultData resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    getUserinfo();
                                }
                                Toast.makeText(getBaseContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

    private void updateAvatar(MultipartBody.Part pic) {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .updatevatar(userid, pic)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData>() {
                            @Override
                            public void accept(ResultData resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    getUserinfo();
                                }
                                Toast.makeText(getBaseContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
        );
    }

    private void setData(UserVo data) {
        if (data != null) {
            tv_nickname.setText(data.getNickname());
            tv_intro.setText(data.getIntro());
        }
        if (data.getAvatar() != null &&! "".equals(data.getAvatar())) {
            Glide.with(getBaseContext())
                    .load(AppConfig.BASE_URL + data.getAvatar())
                    .into(img_avatar);
        }

       /* if (data.getAvatar()!=null)
            Glide.with(getBaseContext())
            .load(""+data.getAvatar())
            .into(img_avatar);*/
    }

    public void showPortraitDialog() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.img_portrait_xc:
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent, 2);
                        break;
                    case R.id.img_portrait_pz:
                        Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent1, 1);
                        break;
                }
            }
        };
        dialog = new UserPortraitDialog(this, 0, listener);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
        dialog.dismiss();
        if (resultCode != RESULT_OK) {
            Log.d("errer", "canceled or other exception!");
            return;
        }

        Bitmap bitmap = null;

        //启用相机返回图片
        if (requestCode == 1) {
            if (data != null) {
                bitmap = (Bitmap) data.getExtras().get("data");
                bitmap = BitMapUtil.getBitmap(this, bitmap);
                bitmap = BitMapUtil.getZoomImage(bitmap, 20.00);

                img_avatar.setImageBitmap(bitmap);
                img_avatar.setVisibility(View.VISIBLE);

                File file = BitMapUtil.getFile(this, bitmap, bitmap.toString());
            }
        }


        //启用相册返回图片
        if (requestCode == 2) {
            if (data != null) {
                //获取图片定位符
                Uri uri = data.getData();

                Log.d("url", uri.toString());
                File file = new File(BitMapUtil.getRealPathFromUri(this, uri));

                if (file != null) {

                    //将参数封装成RequestBody
                    RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                    body = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);

                    updateAvatar(body);
                }
            }
        }
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
