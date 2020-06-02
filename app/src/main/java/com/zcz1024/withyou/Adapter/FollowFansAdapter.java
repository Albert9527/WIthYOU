package com.zcz1024.withyou.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.liji.circleimageview.CircleImageView;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.MyFriendVo;

import java.util.List;

public class FollowFansAdapter extends RecyclerView.Adapter<FollowFansAdapter.ListViewHolder> {
    private List<MyFriendVo> myFriendVos;
    private BaseFragment.OnItemClickListener listener;
    private Context context;

    public FollowFansAdapter(List<MyFriendVo> myFriendVos) {
        this.myFriendVos = myFriendVos;
    }

    public void setListener(BaseFragment.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<MyFriendVo> myFriendVos) {
        this.myFriendVos = myFriendVos;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_followfans, parent, false);
        return new FollowFansAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        MyFriendVo friendVo = myFriendVos.get(position);

        holder.tv_nickanme.setText(friendVo.getNickname());
        holder.tv_intro.setText(friendVo.getIntro());
       /* if (friendVo.getAvatar()!=null)
            Glide.with(context)
            .load(""+friendVo.getAvatar())
            .into(holder.img_userpic);*/

        if (friendVo.getIstwoway() == 1) {
            holder.btn_state.setText("已互粉");
        }

    }

    @Override
    public int getItemCount() {
        if (myFriendVos != null) {
            return myFriendVos.size();
        } else
            return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img_userpic;
        Button btn_state;
        TextView tv_nickanme, tv_intro;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            img_userpic = itemView.findViewById(R.id.circleImg_userpic_followfans);
            btn_state = itemView.findViewById(R.id.btn_follow_state);
            tv_nickanme = itemView.findViewById(R.id.tv_followfans_nickname);
            tv_intro = itemView.findViewById(R.id.tv_followfans_intro);
        }
    }
}
