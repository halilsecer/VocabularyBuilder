package com.example.vocabularywizard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
     RadioGroup radioGroup;
     RadioButton radioButton;
    Button btn_start_quiz;
    FragmentManager manager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
//        radioGroup = findViewById(R.id.radio_group);
        btn_start_quiz = findViewById(R.id.btn_start_quiz);
        final ArrayList<String> list = new ArrayList<>();
        ShowFragmentOne();
    }
    public void ShowFragmentOne(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_lyt,new StartFragment());
        ft.commit();

    }
}