package com.zcz1024.withyou.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zcz1024.withyou.Adapter.ReplyMeAdapter;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.ReplyVo;

import java.util.ArrayList;
import java.util.List;

public class ReplyMeActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private ReplyMeAdapter replyMeAdapter;
    private OnItemClickListener listener;
    private List<ReplyVo> replyVos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_me);

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview_replyme);
        refreshLayout = findViewById(R.id.refresh_replyme);
        replyMeAdapter = new ReplyMeAdapter(null);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /**
         * 自定义listener
         */
        listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {

            }
        };

        DividerItemDecoration itemDecoration = new DividerItemDecoration
                (this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.item_divider));
        recyclerView.addItemDecoration(itemDecoration);

        replyMeAdapter.setListener(listener);
        recyclerView.setAdapter(replyMeAdapter);

        initData();
    }

    private void initData() {
        replyVos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ReplyVo newsVo = new ReplyVo();
            replyVos.add(newsVo);
            replyMeAdapter.setNewsData(replyVos);
        }
    }


    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
