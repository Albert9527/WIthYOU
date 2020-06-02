package com.zcz1024.withyou.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.liji.circleimageview.CircleImageView;
import com.zcz1024.withyou.Activity.My_MainPageActivity;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.AppConfig;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.DynamicVo;

import java.util.List;

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.ListViewHolder> {
    private List<DynamicVo> dynamicVos;
    private BaseFragment.OnItemClickListener listener;
    private Context context;
    private View.OnClickListener btnListener;

    public DynamicAdapter(List<DynamicVo> dynamicVos) {
        this.dynamicVos = dynamicVos;
    }

    public void setListener(BaseFragment.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setDynamicVosData(List<DynamicVo> dynamicVos) {
        this.dynamicVos = dynamicVos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_dynamic, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        DynamicVo dynamicVo = dynamicVos.get(position);
        itemViewClickListenr(holder.img_userpic);

        holder.tv_time.setText(dynamicVo.getDyCreateTime());
        holder.tv_content.setText(dynamicVo.getDyContent());
        holder.tv_nickname.setText(dynamicVo.getNickname());

        if (dynamicVo.getDyPictrue() != null) {
            Glide.with(context)
                    .load("http://59.110.221.100:8080/" + dynamicVo.getDyPictrue())
                    .into(holder.img_firstpic);
        } else {
            holder.img_firstpic.setVisibility(View.GONE);
        }
        if (dynamicVo.getUseravatar() != null) {
            Glide.with(context)
                    .load(AppConfig.BASE_URL + dynamicVo.getUseravatar())
                    .into(holder.img_userpic);
        }else {
            holder.img_userpic.setImageResource(R.drawable.cat);
        }
    }

    private void itemViewClickListenr(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.circleImg_dynamic_userpic:
                        context.startActivity(new Intent(context, My_MainPageActivity.class));
                        break;
                    case R.id.img_dynamic_firstpic:

                        break;
                    case R.id.img_dynamic_more:
                        break;
                    case R.id.tv_dynamic_nickname:
                        break;
                    case R.id.tv_dynamic_content:
                        break;
                    case R.id.tv_dynamic_time:
                        break;
                    case R.id.tv_dynamic_praise:
                        break;
                    case R.id.tv_dynamic_comment:
                        break;
                    case R.id.tv_dynamic_shareNum:
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (dynamicVos != null)
            return dynamicVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img_userpic;
        ImageView img_firstpic, img_more;
        TextView tv_nickname, tv_time, tv_content, prasieNum, commentNum, shareNum;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            img_userpic = itemView.findViewById(R.id.circleImg_dynamic_userpic);

            img_firstpic = itemView.findViewById(R.id.img_dynamic_firstpic);
            img_more = itemView.findViewById(R.id.img_dynamic_more);

            tv_nickname = itemView.findViewById(R.id.tv_dynamic_nickname);
            tv_content = itemView.findViewById(R.id.tv_dynamic_content);
            tv_time = itemView.findViewById(R.id.tv_dynamic_time);
            prasieNum = itemView.findViewById(R.id.tv_dynamic_praise);
            commentNum = itemView.findViewById(R.id.tv_dynamic_comment);
            shareNum = itemView.findViewById(R.id.tv_dynamic_shareNum);
        }
    }
}
