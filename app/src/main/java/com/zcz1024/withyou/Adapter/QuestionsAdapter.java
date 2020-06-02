package com.zcz1024.withyou.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.QsBankVo;
import com.zcz1024.withyou.pojoVO.QuestionsVo;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ListViewHolder> {
    private List<QsBankVo> qsBankVos;
    private BaseActivity.OnItemClickListener listener;

    public QuestionsAdapter(List<QsBankVo> qsBankVos) {
        this.qsBankVos = qsBankVos;
    }

    public void setListener(BaseActivity.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setQuestionsVos(List<QsBankVo> qsBankVos) {
        this.qsBankVos = qsBankVos;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_questions_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {

        QsBankVo qsBankVo = qsBankVos.get(position);

        if (qsBankVo!=null){
            holder.qs_name.setText(qsBankVo.getQsName());
            holder.publishTime.setText(qsBankVo.getQsCreateTime());
            holder.qs_content.setText(qsBankVo.getQsIntro());
            holder.qs_num.setText(qsBankVo.getQsTotal()+"");
        }

        holder.qs_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.itemClick(position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (qsBankVos != null)
            return qsBankVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView qs_name, qs_content, qs_num, publishTime;
        Button qs_details;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            qs_name = itemView.findViewById(R.id.tv_questions_name);
            qs_content = itemView.findViewById(R.id.tv_questions_content);
            qs_num = itemView.findViewById(R.id.tv_questions_num);
            publishTime = itemView.findViewById(R.id.tv_questions_time);
            qs_details = itemView.findViewById(R.id.btn_question_details);
        }
    }
}
