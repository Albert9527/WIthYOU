package com.zcz1024.withyou.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zcz1024.withyou.Fragments.DynamicFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.DynamicDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.Util.BitMapUtil;
import com.zcz1024.withyou.Util.UserPortraitDialog;
import com.zcz1024.withyou.entity.Dynamic;
import com.zcz1024.withyou.pojoVO.DynamicVo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PublishDynamicActivity extends BaseActivity {
    private UserPortraitDialog dialog;
    private EditText et_dyContent;
    private MultipartBody.Part body;
    private String userid;
    private ImageView img_dy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_dynamic);
        intView();
    }

    private void intView() {
        et_dyContent = findViewById(R.id.et_dynamic_content);
        img_dy = findViewById(R.id.img_dynmic_add1);

        userid = AcountInfo.geteditInfo(getBaseContext(), "userid");
    }

    public void addDynamic(View view) {
        switch (view.getId()) {
            case R.id.btn_dynmic_send:
                pubDynamic(body);
                break;
            case R.id.btn_dynmic_addimg:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                }
                showPortraitDialog();
                break;
        }
    }

    public void imgviewclick(View view) {
        view.setVisibility(View.GONE);
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

    private void pubDynamic(MultipartBody.Part pic) {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(DynamicDataService.class)
                        .addDynamic(userid, et_dyContent.getText().toString(), pic)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData>() {
                            @Override
                            public void accept(ResultData resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    PublishDynamicActivity.this.finish();
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

    private void pubDynamicNoPic() {
        addDisposable(
                RetrofitFactory.getRetrofit()
                .create(DynamicDataService.class)
                .pubNopic(userid, et_dyContent.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultData>() {
                    @Override
                    public void accept(ResultData resultData) throws Exception {
                        if (resultData.isSuccess()){
                            PublishDynamicActivity.this.finish();
                        }
                        Toast.makeText(getBaseContext(),resultData.getMsg(),Toast.LENGTH_LONG).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getBaseContext(),throwable.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })
        );
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

                img_dy.setImageBitmap(bitmap);
                img_dy.setVisibility(View.VISIBLE);

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
                img_dy.setVisibility(View.VISIBLE);
                img_dy.setImageURI(uri);
                if (file != null) {

                    //将参数封装成RequestBody
                    RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                    body = MultipartBody.Part.createFormData("pic", file.getName(), requestFile);

                }
            }
        }
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
