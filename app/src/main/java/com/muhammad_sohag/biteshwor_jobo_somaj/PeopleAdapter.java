package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.content.Context;
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

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private Context context;
    private String[] names,numbers;
    int lastPosition =0;

    public PeopleAdapter(Context context, String[] names,String[] numbers) {
        this.context = context;
        this.names = names;
        this.numbers = numbers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.names.setText(names[position]);
        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is number: "+numbers[position], Toast.LENGTH_SHORT).show();
            }
        });

        setAnim(holder.itemView,position);

    }
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
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView names;
        ImageView callBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            names = itemView.findViewById(R.id.people_text_view);
            callBtn = itemView.findViewById(R.id.people_call_btn);
        }
    }
}
