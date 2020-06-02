package com.zcz1024.withyou.Activity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zcz1024.withyou.Adapter.PraiseMeAdpater;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.PariseVo;

import java.util.ArrayList;
import java.util.List;

public class ReceivedPraiseActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private PraiseMeAdpater praiseMeAdpater;
    private List<PariseVo> pariseVos;
    private OnItemClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_praise);

        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerview_praise);
        refreshLayout = findViewById(R.id.refresh_myparise);
        praiseMeAdpater = new PraiseMeAdpater(null);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecoration = new DividerItemDecoration
                (this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.item_divider));

        recyclerView.addItemDecoration(itemDecoration);

        listener = new OnItemClickListener() {
            @Override
            public void itemClick(int position, View view) {

            }
        };

        praiseMeAdpater.setListener(listener);

        recyclerView.setAdapter(praiseMeAdpater);

        initData();
    }

    private void initData() {
        pariseVos = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            PariseVo pariseVo = new PariseVo();
            pariseVos.add(pariseVo);
        }
        praiseMeAdpater.setPariseVos(pariseVos);
    }


    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
