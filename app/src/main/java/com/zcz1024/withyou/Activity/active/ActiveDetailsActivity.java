package com.zcz1024.withyou.Activity.active;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.ActiveDataService;
import com.zcz1024.withyou.Presenter.Service.UserDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.CreatDialogUtil;
import com.zcz1024.withyou.pojoVO.ActiveVo;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ActiveDetailsActivity extends BaseActivity {
    private ImageView img_firstpic;
    private TextView tv_name, tv_organizer, tv_auditor,
            tv_num, tv_duration, tv_time, tv_address, tv_intro;
    private LinearLayout btntag_layout;
    private ActiveVo activeVo;
    private CreatDialogUtil dialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_details);

        intiView();
    }

    private void intiView() {
        img_firstpic = findViewById(R.id.img_active_details_firstpic);

        tv_name = findViewById(R.id.tv_active_details_name);
        tv_organizer = findViewById(R.id.tv_active_details_organizer);
        tv_auditor = findViewById(R.id.tv_active_details_auditor);
        tv_num = findViewById(R.id.tv_active_details_num);
        tv_duration = findViewById(R.id.tv_active_details_duration);
        tv_time = findViewById(R.id.tv_active_details_time);
        tv_address = findViewById(R.id.tv_active_details_address);
        tv_intro = findViewById(R.id.tv_active_details_intro);

        btntag_layout = findViewById(R.id.layout_active_details_btntag);

        Bundle bundle = this.getIntent().getExtras();
        activeVo = (ActiveVo) bundle.getSerializable("active");
        initData(activeVo);
    }


    /**
     * Active_Deatils 界面按钮监听事件方法
     *
     * @Param view:
     * @Return: void
     * @Author: zhudi
     * @Date: 2020/4/18
     */
    public void ContactAndApply(View view) {
        switch (view.getId()) {
            case R.id.btn_active_details_contact:
                break;
            case R.id.btn_active_details_apply:
                showEditDialog(activeVo.getActId());
                break;
            default:
                break;
        }
    }

    private void initData(ActiveVo activeinfo) {
        if (activeinfo != null) {
            tv_name.setText(activeinfo.getActTitle());
            tv_intro.setText(activeinfo.getActIntro());
            tv_address.setText(activeinfo.getActAddress());
            tv_duration.setText(activeinfo.getActDuration() + "H");
            tv_time.setText(activeinfo.getActStartTime() + "");
            tv_num.setText(activeinfo.getActScale() + "");

          /*  if (activeVo.getActPic()!=null){
                Glide.with(this)
                        .load("http://59.110.221.100:8080/img/"+activeVo.getActPic())
                        .into(img_firstpic);
            }*/
        }

    }

    private void ApplyActive(Map map) {
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(UserDataService.class)
                        .ApplyAct(map)
                        .observeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData>() {
                            @Override
                            public void accept(ResultData resultData) throws Exception {
                                if (resultData.isSuccess()) {
                                    Toast.makeText(getBaseContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getBaseContext(), resultData.getMsg(), Toast.LENGTH_SHORT).show();
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

    public void showEditDialog(final String activeId) {
        View.OnClickListener clicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map map = dialogUtil.getApplymap(activeId);
                ApplyActive(map);
            }
        };
        dialogUtil = new CreatDialogUtil(this, 0, clicklistener,activeVo.getActTitle());
        dialogUtil.show();
    }


    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
