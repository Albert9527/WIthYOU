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
import com.zcz1024.withyou.Util.BtnAddUtil;
import com.zcz1024.withyou.pojoVO.MusicVo;

public class MusicRcmActivity extends BaseActivity {
    private ImageView music_firstpic;
    private TextView tv_name,tv_signer,tv_time,tv_intro,tv_reason;
    private LinearLayout layout_btntag;
    private CircleImageView praise_off,praise_on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_rcm);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        MusicVo musicVo = (MusicVo) bundle.get("musicInfo");

        music_firstpic = findViewById(R.id.img_tj_music_details_firstpic);
        tv_name=findViewById(R.id.tv_tj_music_details_name);
        tv_signer = findViewById(R.id.tv_tj_music_details_signer);
        tv_time = findViewById(R.id.tv_tj_music_details_time);
        tv_intro = findViewById(R.id.tv_tj_music_details_intro);
        tv_reason = findViewById(R.id.tv_tj_music_details_reason);
        layout_btntag = findViewById(R.id.layout_tj_musci_details_btntag);

        praise_off = findViewById(R.id.img_tj_music_details_praise_off);
        praise_on = findViewById(R.id.img_tj_music_details_praise_on);

        initData(musicVo);
    }

    private void initData(MusicVo musicVo) {
        tv_name.setText(musicVo.getRcmMusicName());
        tv_signer.setText(musicVo.getSinger());
        tv_intro.setText(musicVo.getRcmMusicIntro());
        tv_reason.setText(musicVo.getRcmReason());
        tv_time.setText(musicVo.getCreateTime());

        if (musicVo.getUpPic()!=null){
            Glide.with(this)
                    .load(AppConfig.BASE_URL+musicVo.getUpPic())
                    .into(music_firstpic);
        }else {
            music_firstpic.setImageDrawable(getDrawable(R.drawable.cat));
        }
        //BtnAddUtil.setTagBtn();
    }

    public void setPraise(View view){
        switch (view.getId()){
            case R.id.img_tj_music_details_praise_off:
                praise_off.setVisibility(View.GONE);
                praise_on.setVisibility(View.VISIBLE);
                break;
            case R.id.img_tj_music_details_praise_on:
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
