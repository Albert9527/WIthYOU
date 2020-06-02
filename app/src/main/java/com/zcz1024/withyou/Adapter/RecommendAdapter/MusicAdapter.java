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
import com.zcz1024.withyou.pojoVO.MusicVo;

import java.util.List;
import java.util.zip.Inflater;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ListViewHolder> {

    private BaseFragment.OnItemClickListener listener;
    private List<MusicVo> musicVos;
    private Context context;
    private View.OnClickListener btnListener;

    public MusicAdapter(List<MusicVo> musicVos) {
        this.musicVos = musicVos;
    }

    public void setListener(BaseFragment.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setMusicVos(List<MusicVo> musicVos) {
        this.musicVos = musicVos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_tj_music, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        MusicVo musicVo = musicVos.get(position);

        holder.music_name.setText(musicVo.getRcmMusicName());
        holder.musci_rcmReason.setText(musicVo.getRcmReason());
        holder.music_createTime.setText(musicVo.getCreateTime());
        if (musicVo.getUpPic()!=null){
            Glide.with(context)
                    .load("http://59.110.221.100:8080/"+musicVo.getUpPic())
                    .into(holder.img_firstpic);
        }else {
            holder.img_firstpic.setImageResource(R.drawable.cat);
        }

        btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(position,v);
            }
        };

        holder.img_firstpic.setOnClickListener(btnListener);
    }

    @Override
    public int getItemCount() {
        if (musicVos!=null)
            return musicVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView music_name, music_createTime, musci_rcmReason;
        ImageView img_firstpic;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            music_name = itemView.findViewById(R.id.tv_tj_music_name);
            music_createTime = itemView.findViewById(R.id.tv_tj_music_time);
            musci_rcmReason = itemView.findViewById(R.id.tv_tj_music_reason);

            img_firstpic = itemView.findViewById(R.id.img_tj_music_firstpic);

        }
    }
}
