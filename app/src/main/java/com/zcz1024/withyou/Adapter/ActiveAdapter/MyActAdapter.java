package com.zcz1024.withyou.Adapter.ActiveAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zcz1024.withyou.Activity.active.ActiveDetailsActivity;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.Util.BtnAddUtil;
import com.zcz1024.withyou.pojoVO.ActiveVo;

import java.util.List;

public class MyActAdapter extends RecyclerView.Adapter<MyActAdapter.ListViewHolder> {
    private List<ActiveVo> activeVos;
    private BaseFragment.OnItemClickListener listener;
    private Context context;
    private View.OnClickListener btnListener;

    public MyActAdapter(List<ActiveVo> activeVos) {
        this.activeVos = activeVos;
    }

    public void setListener(BaseFragment.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setActiveVoData(List<ActiveVo> activeVos) {
        this.activeVos = activeVos;

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_my_act, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        final ActiveVo active = activeVos.get(position);

        holder.name.setText(active.getActTitle());
        holder.scale.setText(active.getActScale()+"");
        holder.time.setText(active.getActStartTime());
       /* if (active.getActPic()!=null)
            Glide.with(context)
                    .load("http://59.110.221.100:8080/img/"+active.getActPic())
                    .into(holder.firstpic);*/

        //动态添加的标签按钮的点击事件监听
        btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                    listener.itemClick(position,v);
               }
        };

        /**
         * 查看详情点击事件，跳转到活动详情页面，
         * 获取数据时通过bundle传递单个对象数据
         */
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("active",active);
                Intent intent = new Intent(context, ActiveDetailsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.apply.setOnClickListener(btnListener);

        holder.name.setText("test");

        //动态添加标签按钮
        BtnAddUtil btnAddUtil = new BtnAddUtil();
       /* btnAddUtil.setTagBtn(, holder.layout, context, btnListener);*/
    }

    @Override
    public int getItemCount() {
        if (activeVos != null)
            return activeVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView firstpic;
        TextView name,scale,time;
        Button details,apply;

        public ListViewHolder(@NonNull View itemView) {

            super(itemView);
            layout = itemView.findViewById(R.id.layout_active_btntag);
            name = itemView.findViewById(R.id.tv_active_name);
            scale=itemView.findViewById(R.id.tv_active_scale);
            time=itemView.findViewById(R.id.tv_active_time);
            firstpic=itemView.findViewById(R.id.tv_active_firstpic);
            details = itemView.findViewById(R.id.btn_my_active_details);
            apply = itemView.findViewById(R.id.btn_my_active_dismiss);
        }
    }
}
