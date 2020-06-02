package com.zcz1024.withyou.Adapter.ActiveAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.MenberVo;

import java.util.List;

public class MenberAdapter extends RecyclerView.Adapter<MenberAdapter.ListViewHolder> {
    private List<MenberVo> menberVos;
    private BaseActivity.OnItemClickListener listener;
    private View.OnClickListener itemListener;
    private Context context;

    public MenberAdapter(List<MenberVo> menberVos) {
        this.menberVos = menberVos;
    }

    public void setMenberVos(List<MenberVo> menberVos) {
        this.menberVos = menberVos;

        //刷新数据
        notifyDataSetChanged();
    }

    public void setListener(BaseActivity.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_act_menber, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
