package com.zcz1024.withyou.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zcz1024.withyou.Activity.BaseActivity;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.QsDetailsVo;

import java.util.ArrayList;
import java.util.List;

public class AnswerQsAdapter extends RecyclerView.Adapter<AnswerQsAdapter.ListViewHolder> {
    private List<QsDetailsVo> qsDetailsVos;
    private BaseActivity.OnItemClickListener listener;
    private List<String> answers = new ArrayList<>(100);
    private List<Integer> socres = new ArrayList(100);
    private Context context;

    public AnswerQsAdapter(List<QsDetailsVo> answerQsVos) {
        this.qsDetailsVos = answerQsVos;
    }

    public void setListener(BaseActivity.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setQuestionsVos(List<QsDetailsVo> answerQsVos) {
        this.qsDetailsVos = answerQsVos;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_questions, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        final QsDetailsVo qsDetailsVo = qsDetailsVos.get(position);
        holder.qscontent.setText(qsDetailsVo.getQsContent());
        holder.option1.setText(qsDetailsVo.getQsOption1());
        holder.option2.setText(qsDetailsVo.getQsOption2());
        if (qsDetailsVo.getQsOption3() != null) {
            holder.option3.setText(qsDetailsVo.getQsOption3());
            holder.option3.setVisibility(View.VISIBLE);
        }
        if (qsDetailsVo.getQsOption4() != null) {
            holder.option4.setText(qsDetailsVo.getQsOption4());
            holder.option4.setVisibility(View.VISIBLE);
        }

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobtn_answer_item_option1:
                        setanswer("A", 0 - qsDetailsVo.getScoreRule() * 3, position);
                        Toast.makeText(context, "点击事件监听", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radiobtn_answer_item_option2:
                        setanswer("B", 1 - qsDetailsVo.getScoreRule() * 1, position);
                        break;
                    case R.id.radiobtn_answer_item_option3:
                        setanswer("C", 2 - qsDetailsVo.getScoreRule() * (-1), position);
                        break;
                    case R.id.radiobtn_answer_item_option4:
                        setanswer("D", 3 - qsDetailsVo.getScoreRule() * (-3), position);
                        break;
                }
            }
        });


    }

    private void setanswer(String answer, int socre, int position) {
        try {
            answers.set(position, answer);
            socres.set(position, socre);
        } catch (IndexOutOfBoundsException e) {
            answers.add(position, answer);
            socres.add(position, socre);
        }
    }

    @Override
    public int getItemCount() {
        if (qsDetailsVos != null)
            return qsDetailsVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView qscontent;
        RadioGroup radioGroup;
        RadioButton option1, option2, option3, option4;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            qscontent = itemView.findViewById(R.id.tv_answer_qscontent);
            radioGroup = itemView.findViewById(R.id.rdgp_answer_item_option);
            option1 = itemView.findViewById(R.id.radiobtn_answer_item_option1);
            option2 = itemView.findViewById(R.id.radiobtn_answer_item_option2);
            option3 = itemView.findViewById(R.id.radiobtn_answer_item_option3);
            option4 = itemView.findViewById(R.id.radiobtn_answer_item_option4);
        }
    }

    public List<String> getAswers() {
        return answers;
    }

    public List<Integer> getSocre() {
        return socres;
    }
}
