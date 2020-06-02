package com.zcz1024.withyou.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zcz1024.withyou.R;

public class QuestionDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);
    }

    public void startAnswer(View view){
        startActivity(new Intent(this,AnswerQsActivity.class));
    }
}
