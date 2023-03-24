package com.gy25m.seoulfestival;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.VH> {
    Context context;
    ArrayList<FestivalItem> festivalItems;

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
        ImageView titleImg;
        TextView title,codeName,place,date;
        public VH(@NonNull View itemView) {
            super(itemView);
            titleImg=itemView.findViewById(R.id.titleImg);
            title=itemView.findViewById(R.id.title);
            codeName=itemView.findViewById(R.id.codeName);
            place=itemView.findViewById(R.id.place);
            date=itemView.findViewById(R.id.date);

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
        }
    }
}
