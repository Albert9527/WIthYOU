package com.zcz1024.withyou.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.liji.circleimageview.CircleImageView;
import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.Activity.active.ActiveDetailsActivity;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.ActiveApplyVo;
import com.zcz1024.withyou.pojoVO.MyFriendVo;

import java.util.List;

public class ActiveApplyCheckAdapter extends RecyclerView.Adapter<ActiveApplyCheckAdapter.ListViewHolder> {
    private List<ActiveApplyVo> applyVos;
    private BaseActivity.OnItemClickListener listener;
    private Context context;
    private View.OnClickListener btnlistener;

    public ActiveApplyCheckAdapter(List<ActiveApplyVo> applyVos) {
        this.applyVos = applyVos;
    }


    public void setListener(BaseActivity.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setApplyVos(List<ActiveApplyVo> applyVos) {
        this.applyVos = applyVos;
        //刷新数据
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_active_apply_check, parent, false);
        return new ActiveApplyCheckAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        //获取数据渲染item
        ActiveApplyVo applyVo = applyVos.get(position);

        holder.tv_nickname.setText(applyVo.getUserId());
        holder.tv_apply_reason.setText(applyVo.getActApplyReason());
        holder.tv_apply_time.setText(applyVo.getActApplyTime());

        /*Glide.with(context)
                .load(""+applyVo.getUserId())
                .into(holder.circleImg_userpic);*/

        /**
         * 查看详情点击事件
         */
        btnlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.itemClick(position, v);
            }
        };

        holder.btn_agree.setOnClickListener(btnlistener);
        holder.btn_refuse.setOnClickListener(btnlistener);

    }


    @Override
    public int getItemCount() {
        if (applyVos != null)
            return applyVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImg_userpic;
        TextView tv_nickname, tv_apply_reason, tv_apply_time;
        ImageView img_more;
        Button btn_agree, btn_refuse;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImg_userpic = itemView.findViewById(R.id.Img_active_apply_check_userpic);
            tv_nickname = itemView.findViewById(R.id.tv_active_apply_check_nickname);
            tv_apply_reason = itemView.findViewById(R.id.tv_active_apply_check_reason);
            tv_apply_time = itemView.findViewById(R.id.tv_active_apply_check_time);
            btn_agree = itemView.findViewById(R.id.btn_active_apply_check_agree);
            btn_refuse = itemView.findViewById(R.id.btn_active_apply_check_refuse);
        }
    }
}
