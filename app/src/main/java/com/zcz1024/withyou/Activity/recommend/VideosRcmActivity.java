package com.zcz1024.withyou.Activity.recommend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liji.circleimageview.CircleImageView;
import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.Presenter.NetWorkData.AppConfig;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.VideoVo;

public class VideosRcmActivity extends BaseActivity {
    private ImageView img_firstpic;
    private TextView tv_title,tv_director,tv_mainPerforme,tv_intro,tv_reason,tv_time;
    private LinearLayout layout_tag;
    private CircleImageView praise_off,praise_on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_rcm);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        VideoVo videoVo = (VideoVo) bundle.get("videoInfo");

        img_firstpic = findViewById(R.id.img_tj_video_details_firstpic);
        tv_title = findViewById(R.id.tv_tj_video_details_name);
        tv_director = findViewById(R.id.tv_tj_video_details_director);
        tv_mainPerforme = findViewById(R.id.tv_tj_video_details_mainPerforme);
        tv_time = findViewById(R.id.tv_tj_video_details_time);
        tv_intro = findViewById(R.id.tv_tj_video_details_intro);
        tv_reason = findViewById(R.id.tv_tj_video_details_reason);
        layout_tag = findViewById(R.id.layout_tj_video_details_btntag);

        praise_off = findViewById(R.id.img_tj_video_details_praise_off);
        praise_on = findViewById(R.id.img_tj_video_details_praise_on);

        initData(videoVo);
    }

    private void initData(VideoVo videoVo) {
        tv_title.setText(videoVo.getRcmvdTitle());
        tv_director.setText(videoVo.getDirector());
        tv_mainPerforme.setText(videoVo.getMainPerforme());
        tv_time.setText(videoVo.getReleaseTime());
        tv_intro.setText(videoVo.getRcmvdIntro());
        tv_reason.setText(videoVo.getRcmReason());

        if (videoVo.getUpPic()!=null){
            Glide.with(this)
                    .load(AppConfig.BASE_URL+videoVo.getUpPic())
                    .into(img_firstpic);
        }else {
            img_firstpic.setImageDrawable(getDrawable(R.drawable.cat));
        }

       // layout_tag = findViewById(R.id.layout_tj_video_details_btntag);
    }

    public void setPraise(View view){
        switch (view.getId()){
            case R.id.img_tj_video_details_praise_off:
                praise_off.setVisibility(View.GONE);
                praise_on.setVisibility(View.VISIBLE);
                break;
            case R.id.img_tj_video_details_praise_on:
                praise_on.setVisibility(View.GONE);
                praise_off.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
