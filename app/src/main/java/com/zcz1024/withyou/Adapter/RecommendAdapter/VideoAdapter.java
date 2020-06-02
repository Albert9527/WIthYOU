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
import com.zcz1024.withyou.Fragments.RecommendFrgments.VideoFragment;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.MovieVo;
import com.zcz1024.withyou.pojoVO.VideoVo;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ListViewHolder> {

    private List<VideoVo> videoVos;
    private VideoFragment.OnItemClickListener listener;
    private View.OnClickListener btnlistener;
    private Context context;

    public VideoAdapter(List<VideoVo> videoVos) {
        this.videoVos = videoVos;
    }

    public void setVideoVos(List<VideoVo> videoVos) {
        this.videoVos = videoVos;
        notifyDataSetChanged();
    }

    public void setListener(VideoFragment.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_tj_video, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        VideoVo videoVo = videoVos.get(position);

        holder.videoname.setText(videoVo.getRcmvdTitle());
        holder.video_rcmReason.setText(videoVo.getRcmReason());
        holder.video_createTime.setText(videoVo.getReleaseTime());

        if (videoVo.getUpPic()!=null){
            Glide.with(context)
                    .load("http://59.110.221.100:8080/"+videoVo.getUpPic())
                    .into(holder.firstPic);
        }else {
            holder.firstPic.setImageResource(R.drawable.cat);
        }

        btnlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.itemClick(position,v);
                }
            }
        };

        holder.firstPic.setOnClickListener(btnlistener);
    }

    @Override
    public int getItemCount() {
        if (videoVos!=null)
            return videoVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView videoname,video_createTime,video_rcmReason;
        ImageView firstPic;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            videoname = itemView.findViewById(R.id.tv_tj_video_name);
            video_createTime = itemView.findViewById(R.id.tv_tj_video_time);
            video_rcmReason = itemView.findViewById(R.id.tv_tj_video_reason);

            firstPic = itemView.findViewById(R.id.img_tj_video_firstpic);
        }
    }
}
