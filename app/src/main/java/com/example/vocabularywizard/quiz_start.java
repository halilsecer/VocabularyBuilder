package com.example.vocabularywizard;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link quiz_start#newInstance} factory method to
 * create an instance of this fragment.
 */
public class quiz_start extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public quiz_start() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment quiz_start.
     */
    // TODO: Rename and change types and number of parameters
    public static quiz_start newInstance(String param1, String param2) {
        quiz_start fragment = new quiz_start();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private TextView texview1;
    private TextView texview2;
    private TextView texview3;
    private TextView texview4;
    private TextView textView5;
    private TextView question;
    private Random random = new Random();

    public static ArrayList<String> options = new ArrayList<>();
    public static ArrayList<String> istenilenkelimelerlist = new ArrayList<>();
    public static ArrayList<String> istenilendefinitionlist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_quiz_start, container, false);
        texview1 = rootView.findViewById(R.id.tv_choice_1);
        texview2 = rootView.findViewById(R.id.tv_choice_2);
        texview3 = rootView.findViewById(R.id.tv_choice_3);
        texview4 = rootView.findViewById(R.id.tv_choice_4);
        //textView5 = rootView.findViewById(R.id.tv_point);


        question = rootView.findViewById(R.id.quiztextview);

        String dbstr = StartFragment.spinneritem;
        DatabaseReference okumayeni=database.getReference().child(dbstr);
        okumayeni.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                istenilenkelimelerlist.clear();
                istenilendefinitionlist.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Words words = snapshot.getValue(Words.class);
                    String txt = words.getWord() +":"+words.getDefinition() ;
                    String istenilenkelime = txt.split(":")[0];
                    istenilenkelimelerlist.add(istenilenkelime);
                    String istenilendefinition = txt.split(":")[1];
                    istenilendefinitionlist.add(istenilendefinition);
                }

                String dogru = StartFragment.list12.get(StartFragment.position);
                 System.out.println(istenilenkelimelerlist);
                String definition="";

                for (int i=0; i<StartFragment.list12.size(); i++){
                    if(istenilenkelimelerlist.get(i).equals(dogru)){
                        System.out.println(istenilenkelimelerlist.get(i));
                         definition = istenilendefinitionlist.get(i);
                        System.out.println(definition);
                    }
                }
                options.clear();
                options.add(dogru);
                 while (options.size() != 4 ){
                    int rnd = random.nextInt(9);
                            //StartFragment.list.get(rnd) != answer && !(StartFragment.list.get(rnd)in options)
                    while ( !( (StartFragment.list12.get(rnd)).equals(dogru)) && !(options.contains(StartFragment.list12.get(rnd))) ){
                        options.add(StartFragment.list12.get(rnd));
                         break;
                    }

                }
                Collections.shuffle(options);
                texview1.setText(options.get(0));
                texview2.setText(options.get(1));
                texview3.setText(options.get(2));
                texview4.setText(options.get(3));
                question.setText(definition);


                texview1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check(options.get(0));
                     }
                });
                texview2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check(options.get(1));
                    }
                });
                texview3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check(options.get(2));
                    }
                });
                texview4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check(options.get(3));
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        return rootView;
    }
    public void check(String cevap){
            if (StartFragment.position == 9){
                System.out.println("++++++++++++finisisis");
                //textView5.setText(StartFragment.point);
                StartFragment.position =0;
                replace();
             }
            else {
                if(StartFragment.list12.get(StartFragment.position) == cevap ){
                    StartFragment.point++;
                }
                StartFragment.position++;
                refresh();
            }
        System.out.println(StartFragment.point+"++++++++++++++++++");

    }
    public void replace(){
        System.out.println( "asdasdasdasdasdasd");
                getFragmentManager().beginTransaction()
                .add(R.id.frame_lyt,new FinishFragment  ())
                .remove(quiz_start.this)
                .commit();
    }
    public void refresh(){
        getFragmentManager().beginTransaction()
                .add(R.id.frame_lyt, new quiz_start())
                .remove(quiz_start.this)
                .commit();
    }
}