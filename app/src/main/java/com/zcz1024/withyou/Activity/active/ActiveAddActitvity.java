package com.zcz1024.withyou.Activity.active;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.Fragments.ActiveFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.ActiveDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.AcountInfo;
import com.zcz1024.withyou.Util.BitMapUtil;
import com.zcz1024.withyou.Util.DiaoLogUtil;
import com.zcz1024.withyou.Util.UserPortraitDialog;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

public class ActiveAddActitvity extends BaseActivity {
    private EditText editText_name, editText_time1, editText_duration;
    private EditText editText_scale, editText_address, editText_intro;
    private EditText et_tag;
    private RadioButton radioButton;
    private MultipartBody.Part picbody;
    private UserPortraitDialog dialog;
    private ImageView img_active_pic;
    private Date actStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_add_actitvity);
        initView();
    }

    private void initView() {
        editText_name = findViewById(R.id.et_activeAdd_name);
        editText_time1 = findViewById(R.id.et_activeAdd_time1);
        editText_duration = findViewById(R.id.et_activeAdd_duration);
        editText_scale = findViewById(R.id.et_activeAdd_scale);
        editText_address = findViewById(R.id.et_activeAdd_address);
        editText_intro = findViewById(R.id.et_activeAdd_intro);
        radioButton = findViewById(R.id.RadioBtn_activeAdd);
        img_active_pic = findViewById(R.id.img_add_active_img);
        et_tag = findViewById(R.id.et_activeAdd_tag);

        editText_time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog pickerDialog = new DatePickerDialog(ActiveAddActitvity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String date = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
                                actStartTime = new Date(year, month + 1, dayOfMonth);
                                editText_time1.setText(date);
                            }
                        }, 2020, 5, 20);
                pickerDialog.show();
            }
        });
    }

    private Map getFormData() {
        Map<String, Object> newActive = new HashMap();

        if (editText_name.getText() != null)
        newActive.put("actTitle", editText_name.getText().toString());
        newActive.put("userId", AcountInfo.geteditInfo(this, "userid"));
        newActive.put("actAddress", editText_address.getText().toString());
        newActive.put("actIntro", editText_intro.getText().toString());
        newActive.put("actScale", Integer.parseInt(editText_scale.getText().toString()));
        newActive.put("actDuration", Integer.parseInt(editText_duration.getText().toString()));
        newActive.put("actTag", et_tag.getText().toString());
        newActive.put("actStartTime",actStartTime);
            /*newActive.put("actPic",);
            newActive.put("actTag",spinner.);*/
        //Toast.makeText(getBaseContext(), newActive.toString(), Toast.LENGTH_LONG).show();
        Log.d("zd>>>>>>>>>>", newActive.toString());
        return newActive;
    }

    private RequestBody convertToRequestBody(Object param) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), (String) param);
        return requestBody;
    }

    public void addActiveImg(View view) {
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

    public void Submit(View view) {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(ActiveDataService.class)
                        .addActive(getFormData(),picbody)
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData>() {
                            @Override
                            public void accept(ResultData resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    ActiveAddActitvity.this.finish();
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

                img_active_pic.setImageBitmap(bitmap);
                img_active_pic.setVisibility(View.VISIBLE);

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
                img_active_pic.setVisibility(View.VISIBLE);
                img_active_pic.setImageURI(uri);
                if (file != null) {

                    //将参数封装成RequestBody
                    RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                    picbody = MultipartBody.Part.createFormData("pic", file.getName(), requestFile);

                }
            }
        }
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
