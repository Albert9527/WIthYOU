package com.zcz1024.withyou.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.MyapplyVo;

import java.util.List;

public class MyApplyAdapter extends RecyclerView.Adapter<MyApplyAdapter.ListViewHolder> {

    private List<MyapplyVo> myapplyVos;
    private Context context;

    public MyApplyAdapter(List<MyapplyVo> myapplyVos) {
        this.myapplyVos = myapplyVos;
    }

    public void setMyapplyVos(List<MyapplyVo> myapplyVos) {
        this.myapplyVos = myapplyVos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_myapply, parent, false);
        return new MyApplyAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        MyapplyVo myapplyVo = myapplyVos.get(position);

        if (myapplyVo!=null){
            Log.d("logd","yyyyyyynotNull");
        }

        holder.tv_actNanme.setText(myapplyVo.getActname());
        holder.tv_applyTime.setText(myapplyVo.getCreateTime());
        holder.tv_reason.setText(myapplyVo.getReason());
        if (myapplyVo.getState().equals(1)) {
            holder.tv_state.setText("已通过");
        } else if (myapplyVo.getState().equals(-1)) {
            holder.tv_state.setText("未通过");
        }else {
            holder.tv_state.setText("待审核");
        }
    }

    @Override
    public int getItemCount() {
        if (myapplyVos != null)
            return myapplyVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_actNanme, tv_applyTime, tv_state,tv_reason;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_actNanme = itemView.findViewById(R.id.tv_myapply_actname);
            tv_applyTime = itemView.findViewById(R.id.tv_myapply_applytime);
            tv_state = itemView.findViewById(R.id.tv_myapply_state);
            tv_reason = itemView.findViewById(R.id.tv_myapply_actreason);
        }
    }
}
