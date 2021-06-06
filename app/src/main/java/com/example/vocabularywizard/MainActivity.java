package com.example.vocabularywizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    Button btn_verb;
    Button btn_adj;
    Button btn_adv;
    Button btn_phrs;
    Button btn_quiz;
    Button btn_user;
    ImageButton btn_image_cmb;
    ImageButton btn_image_oxf;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_verb = findViewById(R.id.btn_verb);
        btn_adj = findViewById(R.id.btn_adj);
        btn_adv = findViewById(R.id.btn_adv);
        btn_phrs = findViewById(R.id.btn_phrs);
        btn_quiz = findViewById(R.id.btn_quiz);
        btn_user= findViewById(R.id.btn_user_list);
        btn_image_cmb = findViewById(R.id.btn_image_cambridge);
        btn_image_oxf = findViewById(R.id.btn_image_oxford);
        btn_verb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,VerbActivity.class));
            }
        });
        btn_adj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdjectiveActivity.class));
            }
        });
      btn_adv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdverbActivity.class));
            }
        });
          btn_phrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PhraseActivity.class));
            }
        });
        btn_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,QuizActivity.class));
            }
        });
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UserList.class));
            }
        });
        btn_image_oxf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://www.oxfordlearnersdictionaries.com/definition/english/"));
                startActivity(myWebLink);
            }
        });
        btn_image_cmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://dictionary.cambridge.org/tr/"));
                startActivity(myWebLink);
            }
        });
    }
}