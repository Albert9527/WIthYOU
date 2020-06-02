package com.zcz1024.withyou.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.liji.circleimageview.CircleImageView;
import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.NewsVo;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ListViewHolder> {
    private List<NewsVo> newsVos;
    private BaseActivity.OnItemClickListener listener;

    public NewsAdapter(List<NewsVo> newsVos) {
        this.newsVos = newsVos;
    }

    public void setListener(BaseActivity.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setNewsData(List<NewsVo> newsVos) {
        this.newsVos = newsVos;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_msg,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (newsVos != null)
            return newsVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img_userpic;
        TextView tv_nickname,tv_msgcontent,tv_time;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            img_userpic = itemView.findViewById(R.id.circleImg_item_msg_userpic);
            tv_nickname = itemView.findViewById(R.id.tv_item_msg_nickname);
            tv_time = itemView.findViewById(R.id.tv_item_msg_time);
            tv_msgcontent = itemView.findViewById(R.id.tv_item_msg_content);
        }
    }
}
