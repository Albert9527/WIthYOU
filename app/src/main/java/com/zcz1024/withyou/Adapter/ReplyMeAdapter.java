package com.zcz1024.withyou.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.NewsVo;
import com.zcz1024.withyou.pojoVO.ReplyVo;

import java.util.List;

public class ReplyMeAdapter extends RecyclerView.Adapter<ReplyMeAdapter.ListViewHolder> {
    private List<ReplyVo> replyVos;
    private BaseActivity.OnItemClickListener listener;

    public ReplyMeAdapter(List<ReplyVo> replyVos) {
        this.replyVos = replyVos;
    }

    public void setListener(BaseActivity.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setNewsData(List<ReplyVo> replyVos) {
        this.replyVos = replyVos;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_replyme,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (replyVos != null)
            return replyVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
