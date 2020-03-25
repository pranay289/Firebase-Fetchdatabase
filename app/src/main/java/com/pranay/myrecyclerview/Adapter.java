package com.pranay.myrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Myholder> {



    ArrayList<Mylist> mylists;
    Context context;

    public Adapter(ArrayList<Mylist> mylists ,Context context){

        this.mylists=mylists;
        this.context=context;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.view,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {

        Mylist arrayList=mylists.get(position);

        Picasso.get().load(arrayList.getImage()).into(holder.imageView);
        holder.title.setText(arrayList.getTitle());
        holder.des.setText(arrayList.getDescription());




    }

    @Override
    public int getItemCount() {
        return mylists.size();
    }

    public class Myholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title,des;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.myimg);
            title=itemView.findViewById(R.id.title);
            des=itemView.findViewById(R.id.des);


        }
    }
}
