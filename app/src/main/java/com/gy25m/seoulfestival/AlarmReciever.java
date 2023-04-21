package com.gy25m.seoulfestival;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager= (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        // 알림객체를 생성해주는 건축가(Builder)객체 생성
        NotificationCompat.Builder builder= null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            //알림 채널 객체 생성
            NotificationChannel channel= new NotificationChannel("ch01","Ex58 channel", NotificationManager.IMPORTANCE_HIGH);

            //매니저에게 채널을 등록하기 전에..사운드 작업해야 함.
            notificationManager.createNotificationChannel(channel);
            builder= new NotificationCompat.Builder(context, "ch01");
        }else{
            builder= new NotificationCompat.Builder(context, "");

        }
        builder.setSmallIcon(R.drawable.sf);


        //상태바를 드래그하여 아래로 내리면 보이는 알림창(확장 상태바)의 설정
        builder.setContentTitle(intent.getStringExtra("title"));
        builder.setContentText(intent.getStringExtra("place"));
        Notification notification= builder.build();

        // 알림 매니저에게 알림을 보이도록 요청
        notificationManager.notify(100, notification);




    }
}