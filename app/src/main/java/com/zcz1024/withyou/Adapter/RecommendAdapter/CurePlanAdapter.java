package com.zcz1024.withyou.Adapter.RecommendAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.CurePlanVo;

import java.util.List;

public class CurePlanAdapter extends RecyclerView.Adapter<CurePlanAdapter.ListViewHolder> {

    private Context context;
    private List<CurePlanVo> curePlanVos;
    private BaseFragment.OnItemClickListener listener;
    private View.OnClickListener btnlistener;

    public CurePlanAdapter(List<CurePlanVo> curePlanVos) {
        this.curePlanVos = curePlanVos;
    }

    public void setCurePlanVos(List<CurePlanVo> curePlanVos) {
        this.curePlanVos = curePlanVos;

        //刷新数据
        notifyDataSetChanged();
    }

    public void setListener(BaseFragment.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_tj_cureplan, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        CurePlanVo curePlanVo = curePlanVos.get(position);

        holder.cureplan_name.setText(curePlanVo.getRcmcpName());
        holder.cureplan_createTime.setText(curePlanVo.getCreateTime());
        holder.cureplan_rcmReason.setText(curePlanVo.getRcmcpReason());

    }

    @Override
    public int getItemCount() {
        if (curePlanVos!=null)
            return curePlanVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView cureplan_name, cureplan_createTime, cureplan_rcmReason;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            cureplan_name = itemView.findViewById(R.id.tv_tj_cureplan_name);
            cureplan_createTime = itemView.findViewById(R.id.tv_tj_cureplan_time);
            cureplan_rcmReason = itemView.findViewById(R.id.tv_tj_cureplan_reason);

        }
    }
}
