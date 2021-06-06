package com.example.vocabularywizard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MemorizedActivity extends AppCompatActivity {
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    private DatabaseReference rootDatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorized);

        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("UserList");


         String ptr = MessageService.word;//getIntent().getStringExtra("isim");
        System.out.println(ptr);
//
        rootDatabaseref.child(ptr).removeValue();


        startActivity(new Intent(MemorizedActivity.this,MainActivity.class));

    }
}