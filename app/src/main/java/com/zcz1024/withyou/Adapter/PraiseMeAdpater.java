package com.zcz1024.withyou.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liji.circleimageview.CircleImageView;
import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.PariseVo;

import java.util.List;

public class PraiseMeAdpater extends RecyclerView.Adapter<PraiseMeAdpater.ListViewHolder> {
    private List<PariseVo> pariseVos;
    private BaseActivity.OnItemClickListener listener;

    public PraiseMeAdpater(List<PariseVo> pariseVos) {
        this.pariseVos = pariseVos;
    }

    public void setListener(BaseActivity.OnItemClickListener listener) {
        this.listener = listener;
    }

    //刷新数据
    public void setPariseVos(List<PariseVo> pariseVos) {
        this.pariseVos = pariseVos;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myparise,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (pariseVos!=null)
            return pariseVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img_userpic;
        TextView tv_notice,tv_time,tv_target;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            img_userpic = itemView.findViewById(R.id.circleImg_praise_userpic);
            tv_notice = itemView.findViewById(R.id.tv_praise_notice);
            tv_time = itemView.findViewById(R.id.tv_praise_time);
            tv_target = itemView.findViewById(R.id.tv_praise_target);
        }
    }
}
