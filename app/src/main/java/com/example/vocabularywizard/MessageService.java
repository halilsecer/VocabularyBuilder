package com.example.vocabularywizard;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.Random;


public class MessageService extends FirebaseMessagingService {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    EditText editText  ;
    public static String word;
    public static String word_name;
    public static String definition;
    public static String sentence;
    public static String synonym;
    Random random = new Random();
    static String kanalId = "kanalId";
    String kanalAd = "kanalAd";
    String kanalTanım = "kanalTanım";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
          // word = remoteMessage.getNotification().getTitle();
        // String icerik= remoteMessage.getNotification().getBody();
    //    Log.e("Baslık", remoteMessage.getNotification().getTitle());
   //     Log.e("İcerik", remoteMessage.getNotification().getBody());
        final ArrayList<String> list = new ArrayList<>();
        bildirimOlustur(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }
    public void bildirimOlustur(String baslik, String mesaj) {

        DatabaseReference myRef = database.getReference("Verb");

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),kanalId);
        NotificationManager bildirimYoneticisi =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent ıntent = new Intent(MessageService.this, NotificationActivity.class);
        ıntent.setAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        ıntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)    ;
        PendingIntent gidilecekIntent = PendingIntent.getActivity(this, 1, ıntent, PendingIntent.FLAG_CANCEL_CURRENT);



        Intent ıntentcancel = new Intent(MessageService.this, MemorizedActivity.class);
        PendingIntent gidilecekIntentcancel = PendingIntent.getActivity(this, 2, ıntentcancel, PendingIntent.FLAG_CANCEL_CURRENT);


        final ArrayList<Words> list_def = new ArrayList<>();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel kanal = bildirimYoneticisi.getNotificationChannel(kanalId);

            if (kanal == null) {
                kanal = new NotificationChannel(kanalId, kanalAd, kanalOnceligi);
                kanal.setDescription(kanalTanım);
                bildirimYoneticisi.createNotificationChannel(kanal);
            }
            final ArrayList<String> list_random = new ArrayList<>();

            DatabaseReference okuma = database.getReference("UserList");
            okuma.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Words words = snapshot.getValue(Words.class);
                        String definition   =   words.getDefinition();
                        list_def.add(words);
                        String wrd = words.getWord();
                         list_random.add(wrd);
                    }
                    int rnd = random.nextInt(list_random.size()-1);
                    String random_word = list_random.get(rnd);
                    for (int i=0;i<list_random.size();i++){
                        if(list_def.get(i).word.equals(random_word)){
                            MessageService.definition = list_def.get(i).definition;
                            MessageService.sentence = list_def.get(i).sentence;
                            MessageService.synonym = list_def.get(i).synonym;
                         }
                    }
                    MessageService.word = random_word;
                    //gidilecekIntentcancel.putExtra("isim",random_word);
                    builder.setContentTitle(random_word)  // gerekli
                            .setContentText( MessageService.definition)  // gerekli
                            .setSmallIcon(R.drawable.yatis) // gerekli
                            .setContentIntent(gidilecekIntent)
                            .setAutoCancel(true)
                            .setOngoing(false)
                            .addAction(R.mipmap.ic_launcher,"Memorized",gidilecekIntentcancel)
                            .addAction(R.mipmap.ic_launcher,"Remind Later",gidilecekIntent);
                    bildirimYoneticisi.notify(1,builder.build());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });

        } else {
            DatabaseReference okuma = database.getReference("UserList");
            final ArrayList<String> list_random2 = new ArrayList<>();
            okuma.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Words words = snapshot.getValue(Words.class);
                        list_def.add(words);
                        String txt = words.getWord();
                        System.out.println(txt);
                        list_random2.add(txt);
                    }
                    int rnd = random.nextInt(list_random2.size()-1);

                    String random_word = list_random2.get(rnd);
                    for (int i=0;i<list_random2.size();i++){
                        if(list_def.get(i).word.equals(random_word)){
                            MessageService.definition = list_def.get(i).definition;
                            MessageService.sentence = list_def.get(i).sentence;
                            MessageService.synonym = list_def.get(i).synonym;
                        }
                    }
                    MessageService.word = random_word;
                    builder.setContentTitle(random_word)  // gerekli
                            .setContentText(  MessageService.definition)  // gerekli
                            .setSmallIcon(R.drawable.yatis) // gerekli
                            .setAutoCancel(true)
                            .setOngoing(false)
                            .setContentIntent(gidilecekIntent)
                            .setPriority(Notification.PRIORITY_HIGH)
                            .addAction(R.mipmap.ic_launcher,"Memorized",gidilecekIntentcancel)
                            .addAction(R.mipmap.ic_launcher,"Remind Later",gidilecekIntent);
                    bildirimYoneticisi.notify(1,builder.build());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }

    }
}