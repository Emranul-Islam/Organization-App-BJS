package com.muhammad_sohag.biteshwor_jobo_somaj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChadaAdapter extends RecyclerView.Adapter<ChadaAdapter.ViewHolder> {
    private Context context;
    private List<ChadaModel> chadaModelList;
    int lastPosition=0;

    public ChadaAdapter(Context context, List<ChadaModel> chadaModelList) {
        this.context = context;
        this.chadaModelList = chadaModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chada_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chadaItemName.setText(chadaModelList.get(position).getChadaName());
        holder.chadaItemTime.setText(chadaModelList.get(position).getChadaTime());
        setAnim(holder.itemView,position);
    }

    //Simple Animation
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
        return chadaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView chadaItemName;
        private TextView chadaItemTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chadaItemName = itemView.findViewById(R.id.chada_item_name);
            chadaItemTime = itemView.findViewById(R.id.chada_item_time);
        }
    }
}
