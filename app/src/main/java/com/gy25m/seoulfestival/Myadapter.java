package com.gy25m.seoulfestival;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;

public class Myadapter extends RecyclerView.Adapter<Myadapter.VH> {
    Context context;
    ArrayList<FestivalItem> festivalItems;
    int year, month, day;
    int hour, minute;
    String titlef,timef;

    public Myadapter(Context context, ArrayList<FestivalItem> festivalItems) {
        this.context = context;
        this.festivalItems = festivalItems;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new VH(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Glide.with(context).load(festivalItems.get(position).titleImg.toString()).into(holder.titleImg);
        holder.title.setText(festivalItems.get(position).title);
        holder.codeName.setText(festivalItems.get(position).codeName);
        holder.place.setText(festivalItems.get(position).place);
        holder.date.setText(festivalItems.get(position).date);
    }

    @Override
    public int getItemCount() {
        return festivalItems.size();
    }

    class VH extends RecyclerView.ViewHolder{
        ImageView titleImg,time;
        TextView title,codeName,place,date;

        public VH(@NonNull View itemView) {
            super(itemView);
            titleImg=itemView.findViewById(R.id.titleImg);
            title=itemView.findViewById(R.id.title);
            codeName=itemView.findViewById(R.id.codeName);
            place=itemView.findViewById(R.id.place);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);

            itemView.setOnClickListener(view -> {
                ImageView diaIv;
                TextView diaTitle,diaGenre,diaPlace,diaDate;
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                View dia=LayoutInflater.from(context).inflate(R.layout.dialog,null);
                builder.setView(dia);
                AlertDialog dialog=builder.create();


                diaIv=dia.findViewById(R.id.dia_iv);
                diaTitle=dia.findViewById(R.id.dia_title);
                diaGenre=dia.findViewById(R.id.dia_genreT);
                diaPlace=dia.findViewById(R.id.dia_palceT);
                diaDate=dia.findViewById(R.id.dia_dateT);
                int n=getLayoutPosition();
                FestivalItem item=festivalItems.get(n);
                Glide.with(context).load(item.titleImg).into(diaIv);
                diaTitle.setText(item.title);
                diaGenre.setText(item.codeName);
                diaPlace.setText(item.place);
                diaDate.setText(item.date);

                dialog.show();

                diaPlace.setOnClickListener(view1 -> {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/link/search/"+item.place));
                    context.startActivity(intent);
                });
            });

            TimePickerDialog.OnTimeSetListener timeSetListener= new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    hour= i;
                    minute= i1;

                    Calendar calendar= Calendar.getInstance();
                    calendar.set(year, month, day, hour, minute, 0);

                    AlarmManager alarmManager= (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

                    int position=getLayoutPosition();
                    FestivalItem item=festivalItems.get(position);

                    Intent intent= new Intent(context, AlarmReciever.class);
                    intent.putExtra("title",item.title);
                    intent.putExtra("place",item.place);
                    //context.sendBroadcast(intent);

                    //알람 설정
                    alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                }
            };
            time.setOnClickListener(view -> {
                DatePickerDialog dialog= new DatePickerDialog(context);

                dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        year= i;
                        month= i1;
                        day= i2;

                        Calendar calendar= Calendar.getInstance();
                        hour= calendar.get(Calendar.HOUR_OF_DAY);
                        minute= calendar.get(Calendar.MINUTE);
                        new TimePickerDialog(context, timeSetListener, hour, minute, true).show();
                    }
                });
                dialog.show();
            });

        }

    }


}
