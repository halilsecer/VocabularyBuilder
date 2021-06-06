package com.example.vocabularywizard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class AdverbActivity extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Random rnd;
    DatabaseReference myRef = database.getReference("Verb");
    DatabaseReference myRefyeni = database.getReference("Verb/"+myRef.push().getKey());
    AlertDialog.Builder dialogbuilder;
    EditText et_multi_def,popup_sentence;
    TextView popup_word,popup_syn;
    AlertDialog dialog;
    Button popup_cancel,popup_add;
    final ArrayList<String> list = new ArrayList<>();
    final ArrayList<Words> istenilenlist = new ArrayList<>();
    static int pst=0;
    static  String def="";
    static    String sent="";
    static    String word="";
    static   String syn="";
    public void createDialog(int position){
        dialogbuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup,null);
        popup_word = (TextView) popupView.findViewById(R.id.tv_pop_word);
        et_multi_def = (EditText) popupView.findViewById(R.id.et_multi_def);
        popup_sentence = (EditText) popupView.findViewById(R.id.tv_pop_sentence);
        popup_syn = (TextView) popupView.findViewById(R.id.tv_pop_syn);

        popup_cancel = (Button) popupView.findViewById(R.id.btn_pop_cancel);
        popup_add = (Button) popupView.findViewById(R.id.btn_pop_add);

        dialogbuilder.setView(popupView);
        dialog = dialogbuilder.create();
        dialog.show();

        String selectedFromList =(String) (listView.getItemAtPosition(position));
        String istenilenkelime = selectedFromList.split(":")[0].toString() ;
        System.out.println(istenilenkelime);
        Toast.makeText(AdverbActivity.this, istenilenkelime, Toast.LENGTH_SHORT).show();
        for (int i=0;i<istenilenlist.size();i++){
            if(istenilenlist.get(i).word.equals(istenilenkelime)){
                def=istenilenlist.get(i).getDefinition();
                et_multi_def.setText(def);
                word=istenilenlist.get(i).getWord();
                popup_word.setText(word);
                sent=istenilenlist.get(i).getSentence();
                popup_sentence.setText(sent);
                syn=istenilenlist.get(i).getSynonym();
                popup_syn.setText(syn);

                //writeNewUser(istenilenlist.get(i).getWord(),istenilenlist.get(i).getDefinition(),istenilenlist.get(i).getSentence(),istenilenlist.get(i).getSynonym());
            }
        }
        popup_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewUser(word,def,sent,syn);

            }
        });
        popup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

    }
    DatabaseReference reference = database.getReference("UserList");
    public void writeNewUser(  String word, String definition,String sentence,String synonym) {
        Words words = new Words(word,definition,sentence,synonym);
        reference.child(word).setValue(words);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adverb);
        listView = findViewById(R.id.listview_adverb);

        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);
        DatabaseReference  okumayeni=database.getReference().child("Adverb");
        okumayeni.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Words words = snapshot.getValue(Words.class);
                    String txt = words.getWord()  ;
                    list.add(txt);
                    istenilenlist.add(words);
                 }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               createDialog(position);
            }
        });
    }
}