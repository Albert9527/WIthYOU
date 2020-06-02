package com.zcz1024.withyou.Activity.recommend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liji.circleimageview.CircleImageView;
import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.Presenter.NetWorkData.AppConfig;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.BookTjVo;

public class BookRcmActivity extends BaseActivity {
    private ImageView img_firstPic;
    private TextView tv_name, tv_press, tv_author, tv_time;
    private TextView tv_intro, tv_reason;
    private CircleImageView praise_off,praise_on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_rcm);
        initView();
    }

    public void initView() {
        Bundle bundle = getIntent().getExtras();
        BookTjVo book = (BookTjVo) bundle.get("bookInfo");

        img_firstPic = findViewById(R.id.img_tj_book_details_firstpic);
        tv_name = findViewById(R.id.tv_tj_book_details_name);
        tv_press = findViewById(R.id.tv_tj_book_details_press);
        tv_author = findViewById(R.id.tv_tj_book_details_author);
        tv_time = findViewById(R.id.tv_tj_book_details_time);
        tv_intro = findViewById(R.id.tv_tj_book_details_intro);
        tv_reason = findViewById(R.id.tv_tj_book_details_reason);
        praise_off = findViewById(R.id.img_tj_book_details_praise_off);
        praise_on = findViewById(R.id.img_tj_book_details_praise_on);

        if (book!=null)
        initData(book);
    }

    private void initData(BookTjVo book) {
        tv_name.setText(book.getRcmbookName());
        tv_author.setText(book.getRcmbookAuthor());
        tv_press.setText(book.getRcmbookPress());
        tv_time.setText(book.getPubDate());
        tv_intro.setText(book.getRcmbookIntro());
        tv_reason.setText(book.getRcmbookReason());

        if (book.getUpPic()!=null){
            Glide.with(this)
                    .load(AppConfig.BASE_URL +book.getUpPic())
                    .into(img_firstPic);
        }else {
            img_firstPic.setImageDrawable(getDrawable(R.drawable.cat));
        }

    }

    public void setPraise(View view){
        switch (view.getId()){
            case R.id.img_tj_book_details_praise_off:
                praise_off.setVisibility(View.GONE);
                praise_on.setVisibility(View.VISIBLE);
                break;
            case R.id.img_tj_book_details_praise_on:
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
