package com.example.vocabularywizard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StartFragment newInstance(String param1, String param2) {
        StartFragment fragment = new StartFragment();
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
    public static int position =0;
    public static int  point  =0;
    public static String spinneritem="";
    static final   ArrayList<String> list12 = new ArrayList<>();
    public final static ArrayList<String> spinnerlist = new ArrayList<>();
    Button btn_start_quiz;
    Spinner spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_start, container, false);
        //radioGroup = rootView.findViewById(R.id.radio_group);
        spinner = rootView.findViewById(R.id.spinner);
        btn_start_quiz = rootView.findViewById(R.id.btn_start_quiz);
        spinnerlist.add("Verb");
        spinnerlist.add("Adverb");
        spinnerlist.add("Adjective");
        spinnerlist.add("Phrase");
         ArrayAdapter adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item,spinnerlist);
         spinner.setAdapter(adapter);
         btn_start_quiz.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 spinneritem   =spinner.getSelectedItem().toString();
                 if(spinneritem.equals("Verb")){
                     DatabaseReference okumayeni=database.getReference().child("Verb");
                     okumayeni.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                 Words words = snapshot.getValue(Words.class);
                                 String txt = words.getWord() ;
                                 StartFragment.list12.add(txt);
                             }
                             Collections.shuffle(list12);
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError error) { }

                     });

                 }
                 if(spinneritem.equals("Adverb")){
                     DatabaseReference okumayeni=database.getReference().child("Adverb");
                     okumayeni.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                 Words words = snapshot.getValue(Words.class);
                                 String txt = words.getWord() ;
                                 StartFragment.list12.add(txt);
                             }
                             Collections.shuffle(list12);
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError error) { }

                     });

                 }
                 if(spinneritem.equals("Adjective")){
                     DatabaseReference okumayeni=database.getReference().child("Adjective");
                     okumayeni.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                 Words words = snapshot.getValue(Words.class);
                                 String txt = words.getWord() ;
                                 StartFragment.list12.add(txt);
                             }
                             Collections.shuffle(list12);
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError error) { }

                     });

                 }
                 if(spinneritem.equals("Phrase")){
                     DatabaseReference okumayeni=database.getReference().child("Phrase");
                     okumayeni.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                             for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                 Words words = snapshot.getValue(Words.class);
                                 String txt = words.getWord() ;
                                 StartFragment.list12.add(txt);
                             }
                             Collections.shuffle(list12);
                         }
                         @Override
                         public void onCancelled(@NonNull DatabaseError error) { }

                     });

                 }
                 replace();
             }
         });
        // Inflate the layout for this fragment
                 /* int ıd_choice = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) rootView.findViewById(ıd_choice);
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(radioButton.getText().equals("Verb") ){


                            btn_start_quiz.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Collections.shuffle(list);
                                    replace();
                                }
                            });

                            System.out.println(list+"---");

                            System.out.println("CHOİCE ! SONU-----------------");
                        }
                    }
                });*/
                 /*

                      String name_choice = (String) radioButton.getText();
                    System.out.println(radioButton.getText());*/
                System.out.println("FRAGMENT GİRMEDİ-----------------");

                /*
                else if(name_choice.equals("Adverb")){
                    DatabaseReference okumayeni=database.getReference().child("Adverb");
                    okumayeni.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list.clear();
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                Words words = snapshot.getValue(Words.class);
                                String txt = words.getWord() + ":" + words.getDefinition();
                                list.add(txt);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) { }
                    });
                }
                else if(name_choice.equals("Adjective"  )){
                    DatabaseReference okumayeni=database.getReference().child("Adjective");
                    okumayeni.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list.clear();
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                Words words = snapshot.getValue(Words.class);
                                String txt = words.getWord() + ":" + words.getDefinition();
                                list.add(txt);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) { }
                    });
                }
                else if(name_choice.contains("Phrase")){
                    DatabaseReference okumayeni=database.getReference().child("Phrase");
                    okumayeni.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            list.clear();
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                                Words words = snapshot.getValue(Words.class);
                                String txt = words.getWord() + ":" + words.getDefinition();
                                list.add(txt);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) { }
                    });
                }
                else{

                }*/
                //Collections.shuffle(list);

        return rootView;
    }
    public void   replace(){
        getFragmentManager().beginTransaction()
                .add(R.id.frame_lyt, new quiz_start())
                .remove(StartFragment.this)
                .commit();
    }
}