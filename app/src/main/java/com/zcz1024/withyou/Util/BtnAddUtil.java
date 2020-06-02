package com.zcz1024.withyou.Util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zcz1024.withyou.R;

import java.util.ArrayList;
import java.util.List;

public class BtnAddUtil {

    public static void setTagBtn(List<String> tags, LinearLayout layout,
                                 Context context, View.OnClickListener btnOnClick) {
        int size = tags.size();
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 10, 20, 10);

        List<Button> childBtns = new ArrayList<>();
        int totoalBtns = 0;
        layout.removeAllViews();


        for (int i = 0; i < size; i++) {
            String item = tags.get(i);
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,60);
            int length = item.length();

            if (length <= 4) { // 根据字数来判断按钮的空间长度, 少于4个当一个按钮
                itemParams.weight = 1;
                totoalBtns++;
            } else if (length <= 8) { // <8个两个按钮空间
                itemParams.weight = 2;
                totoalBtns += 2;
            } else {
                itemParams.weight = 3;
                totoalBtns += 3;
            }

            itemParams.width = 0;
            itemParams.setMargins(10, 10, 10, 10);
           /* Button childBtn = (Button) LayoutInflater.from(context)
                    .inflate(R.layout.item_button, null);*/
            Button childBtn = new Button(context);
            childBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,30));
            childBtn.setBackground(context.getResources().getDrawable(R.drawable.et_boder,null));
            childBtn.setText(item);
            childBtn.setOnClickListener(btnOnClick);
            childBtn.setTag(item);
            childBtn.setLayoutParams(itemParams);
            childBtns.add(childBtn);

            if (totoalBtns >= 4) {
                LinearLayout horizLL = new LinearLayout(context);
                horizLL.setOrientation(LinearLayout.HORIZONTAL);
                horizLL.setLayoutParams(layoutParams);

                for (Button addBtn : childBtns) {
                    horizLL.addView(addBtn);
                }
                layout.addView(horizLL);
                childBtns.clear();
                totoalBtns = 0;
            }
        }
        //最后一行添加一下
        if (!childBtns.isEmpty()) {
            LinearLayout horizLL = new LinearLayout(context);
            horizLL.setOrientation(LinearLayout.HORIZONTAL);
            horizLL.setLayoutParams(layoutParams);

            for (Button addBtn : childBtns) {
                horizLL.addView(addBtn);
            }
            layout.addView(horizLL);
            childBtns.clear();
            totoalBtns = 0;
        }else
            return;

    }

}
