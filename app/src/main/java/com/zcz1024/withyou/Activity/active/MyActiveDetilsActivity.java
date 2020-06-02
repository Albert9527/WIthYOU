package com.zcz1024.withyou.Activity.active;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.Adapter.ActiveAdapter.MenberAdapter;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.MenberVo;

import java.util.List;

public class MyActiveDetilsActivity extends BaseActivity {
    private RefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private MenberAdapter menberAdapter;
    private List<MenberVo> menberVos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_active_detils);
        initView();
    }

    private void initView(){
        recyclerView = findViewById(R.id.recyclerview_member);
        refreshLayout = findViewById(R.id.refresh_member);
        menberAdapter = new MenberAdapter(null);
    }

    @Override
    public void closeActivity(View view) {
        super.closeActivity(view);
    }
}
