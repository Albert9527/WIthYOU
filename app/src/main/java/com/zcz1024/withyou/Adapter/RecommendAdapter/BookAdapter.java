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
import com.zcz1024.withyou.Fragments.RecommendFrgments.BookFragment;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.BookTjVo;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ListViewHolder> {

    private List<BookTjVo> bookTjVos;
    private BookFragment.OnItemClickListener listener;
    private Context context;
    private View.OnClickListener btnlistener;

    public BookAdapter(List<BookTjVo> bookTjVos) {
        this.bookTjVos = bookTjVos;
    }

    public void setOnItemClickListener(BookFragment.OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setbooksData(List<BookTjVo> bookTjVos) {
        this.bookTjVos = bookTjVos;

        //刷新数据
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_tj_book, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        BookTjVo bookTjVo = bookTjVos.get(position);
        holder.bookname.setText("《"+bookTjVo.getRcmbookName()+"》");
        holder.createTime.setText(bookTjVo.getPubDate());
        holder.rcmReason.setText(bookTjVo.getRcmbookReason());
        if (bookTjVo.getFirstPictrue()!=null&&!"".equals(bookTjVo.getFirstPictrue())){
            Glide.with(context)
                    .load("http://59.110.221.100:8080/"+bookTjVo.getFirstPictrue())
                    .into(holder.firstPic);
        }else {
            holder.firstPic.setImageResource(R.drawable.cat);
        }

        btnlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(position,v);
            }
        };

        holder.firstPic.setOnClickListener(btnlistener);
    }

    @Override
    public int getItemCount() {
        if (bookTjVos!=null)
            return bookTjVos.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView bookname,createTime,rcmReason;
        ImageView firstPic;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            bookname = itemView.findViewById(R.id.tv_tj_book_name);
            createTime = itemView.findViewById(R.id.tv_tj_book_time);
            rcmReason = itemView.findViewById(R.id.tv_tj_book_reason);

            firstPic = itemView.findViewById(R.id.img_tj_book_firstpic);
        }
    }
}
