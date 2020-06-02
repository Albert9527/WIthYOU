package com.zcz1024.withyou.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zcz1024.withyou.Adapter.NewsAdapter;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.NewsVo;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class NewsActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private NewsAdapter newsAdapter;
    private OnItemClickListener listener;
    private List<NewsVo> newsVos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initrecyclerView();
    }

    private void initrecyclerView() {
        recyclerView = findViewById(R.id.recyclerview_msgs);
        refreshLayout = findViewById(R.id.refresh_msgs);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(null);

        /**
         * 自定义item的监听
         */
        listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {

            }
        };

        //设置下划线
        DividerItemDecoration itemDecoration =
                new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider,null));
        recyclerView.addItemDecoration(itemDecoration);

        //全屏水滴样式刷新实现
    /*    RefreshUtil.refresh(getContext(), view, R.id.refresh_dynmic,
                new OnRefreshListener() {
                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        loadData();
                        refreshLayout.finishRefresh(1000);
                    }
                });*/

        newsAdapter.setListener(listener);
        recyclerView.setAdapter(newsAdapter);

        initData();
    }

    private void initData(){
        newsVos = new ArrayList<>();
            for (int i=0;i<8;i++){
                NewsVo newsVo = new NewsVo();
                newsVos.add(newsVo);
            }
            newsAdapter.setNewsData(newsVos);
        }

    public void tabclick(View view){
        switch (view.getId()){
            case R.id.news_title_1:
                startActivity(new Intent(this,ReplyMeActivity.class));
                Toast.makeText(getBaseContext(),"1被点击",Toast.LENGTH_LONG).show();
                break;

            case R.id.news_title_2:
                //startActivity(null);
                Toast.makeText(getBaseContext(),"2被点击",Toast.LENGTH_LONG).show();
                break;

            case R.id.news_title_3:
                startActivity(new Intent(this,ReceivedPraiseActivity.class));
                Toast.makeText(getBaseContext(),"3被点击",Toast.LENGTH_LONG).show();
                break;

            case R.id.news_title_4:
                //startActivity(null);
                Toast.makeText(getBaseContext(),"4被点击",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
