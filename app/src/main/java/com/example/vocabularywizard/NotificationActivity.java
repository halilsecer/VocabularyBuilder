package com.example.vocabularywizard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationActivity extends AppCompatActivity {
    TextView textView ;
    TextView textView2;
    Button btn_yes;
    Button btn_no;
     FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("UserList");
    private DatabaseReference rootDatabaseref;
    public static String str="";
    public void writeNewUser(  String word, String definition,String sentence,String synonym) {
        Words words = new Words(word,definition,sentence,synonym);
        reference.child(word).setValue(words);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        textView = findViewById(R.id.tw_word_activity_degisen);
        textView2 = findViewById(R.id.tv_notification_definition);
        btn_no = findViewById(R.id.btn_word_Activity_No);
        btn_yes = findViewById(R.id.btn_word_activity_yes);

        textView.setText(MessageService.word);
        textView2.setText(MessageService.definition);

        rootDatabaseref = FirebaseDatabase.getInstance().getReference().child("UserList");



           str = textView.getText().toString();

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sil
                rootDatabaseref.child(str).removeValue();
                //deleteRecordI(str);
                Toast.makeText(NotificationActivity.this, "Word deleted from your list successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(NotificationActivity.this,MainActivity.class));

                NotificationActivity.this.finish();

                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(1);


            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(NotificationActivity.this, "Word will remain in your list  ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NotificationActivity.this,MainActivity.class));

            }
        });
    }

}