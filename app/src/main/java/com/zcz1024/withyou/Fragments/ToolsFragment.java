package com.zcz1024.withyou.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zcz1024.withyou.Activity.QuestionsActivity;
import com.zcz1024.withyou.R;

public class ToolsFragment extends BaseFragment{
    private Button test_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tools, container, false);

        intiView(view);

        return view;
    }

    private void intiView(View v) {
        test_button = v.findViewById(R.id.btn_tools_testBySelf);
        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QuestionsActivity.class));
            }
        });
    }


}
