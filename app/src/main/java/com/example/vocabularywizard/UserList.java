package com.example.vocabularywizard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText editText_word;
    EditText editText_definition;
    EditText editText_sentence;
    Button btn_show;
    Button btn_add_database;
    ListView listView;

     DatabaseReference reference = database.getReference("UserList");
    public void writeNewUser(  String word, String definition,String sentence,String synonym) {
        Words words = new Words(word,definition,sentence,synonym);
        reference.child(word).setValue(words);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        editText_word = findViewById(R.id.et_add_word);
        editText_definition = findViewById(R.id.et_add_definiton);
        editText_sentence = findViewById(R.id.et_add_sentence);
        btn_show=findViewById(R.id.btn_showadd);
        btn_add_database=findViewById(R.id.btn_add_database);

        editText_word.setVisibility(View.INVISIBLE);
        editText_definition.setVisibility(View.INVISIBLE);
        editText_sentence.setVisibility(View.INVISIBLE);
        btn_add_database.setVisibility(View.INVISIBLE);

        listView = findViewById(R.id.listview_userlist);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayList<Words> istenilenlist = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);
        DatabaseReference okumayeni=database.getReference().child("UserList");
        okumayeni.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Words words = snapshot.getValue(Words.class);
                    String txt = words.getWord() + ":" + words.getDefinition();
                    list.add(txt);
                    istenilenlist.add(words);
                 }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_word.setVisibility(View.VISIBLE);
                editText_definition.setVisibility(View.VISIBLE);
                editText_sentence.setVisibility(View.VISIBLE);
                btn_add_database.setVisibility(View.VISIBLE);
            }
        });
        btn_add_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String def = editText_definition.getText().toString();
            String word = editText_word.getText().toString();
            String sentence = editText_sentence.getText().toString();

        writeNewUser(word,def,sentence,"");
                editText_word.setVisibility(View.INVISIBLE);
                editText_definition.setVisibility(View.INVISIBLE);
                editText_sentence.setVisibility(View.INVISIBLE);
                btn_add_database.setVisibility(View.INVISIBLE);
                View view = UserList.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            hideKeyboard(UserList.this);
            }
        });
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}