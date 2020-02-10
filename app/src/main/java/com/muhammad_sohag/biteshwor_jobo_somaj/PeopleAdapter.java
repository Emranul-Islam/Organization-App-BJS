package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private Context context;
    private List<PeopleModel> modelLit;
    private int lastPosition =0;

    public PeopleAdapter(Context context, List<PeopleModel> modelLit) {
        this.context = context;
        this.modelLit = modelLit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //setup images
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(modelLit.get(position).getPhoto())
                .into(holder.photo);
        holder.names.setText(modelLit.get(position).getName());
        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is number: "+modelLit.get(position).getPhone(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Anything", Toast.LENGTH_SHORT).show();
            }
        });

        //Calling Animation
        setAnim(holder.itemView,position);
    }
    //Making Animation
    public void setAnim(View view,int position){
        if (position>lastPosition){
            ScaleAnimation animation = new ScaleAnimation(0.0f,1.0f,0.0f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(800);
            view.startAnimation(animation);
            lastPosition=position;
        }
    }

    @Override
    public int getItemCount() {
        return modelLit.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView names;
        ImageView callBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.people_image_view);
            names = itemView.findViewById(R.id.people_text_view);
            callBtn = itemView.findViewById(R.id.people_call_btn);
        }
    }
}
