package xyz.emranul.bijos.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.emranul.bijos.R;
import xyz.emranul.bijos.model.BloodModel;

public class BloodAdapter extends RecyclerView.Adapter<BloodAdapter.ViewHolder> {
    private final Context context;
    private final List<BloodModel> bloodModels;
    private int lastPosition = 0;

    public BloodAdapter(Context context, List<BloodModel> bloodModels) {
        this.context = context;
        this.bloodModels = bloodModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.blood_member_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.name.setText(bloodModels.get(position).getName());
        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + bloodModels.get(position).getNumber()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CALL_PHONE},1);
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                context.startActivity(intent);
            }
        });
        String thikana = bloodModels.get(position).getThikana();
        holder.thikana.setText("এলাকা: "+thikana);
        switch (thikana){
            case "বিটেশ্বর":
                holder.viewDesign.setBackgroundColor(Color.RED);
                break;
            case "মাদলা":
                holder.viewDesign.setBackgroundColor(Color.GREEN);
                break;
            case "ডেকরীখোলা":
                holder.viewDesign.setBackgroundColor(Color.CYAN);
                break;
            case "তিনপাড়া":
                holder.viewDesign.setBackgroundColor(Color.MAGENTA);
                break;
            case "বরকোটা":
                holder.viewDesign.setBackgroundColor(Color.YELLOW);
                break;
            case "খানেবাড়ি":
                holder.viewDesign.setBackgroundColor(Color.BLUE);
                break;
            case "চিনামুড়া":
                holder.viewDesign.setBackgroundColor(Color.GRAY);
                break;
            default:
                holder.viewDesign.setBackgroundColor(Color.BLACK);
        }

        setAnim(holder.itemView,position);

    }

    //Making Animation
    private void setAnim(View view, int position) {
        if (position >= lastPosition) {
            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(700);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public int getItemCount() {
        return bloodModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView thikana;
        private final ImageView callBtn;
        private final View viewDesign;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bm_item_name);
            thikana = itemView.findViewById(R.id.bm_item_thikana);
            callBtn = itemView.findViewById(R.id.bm_item_call);
            viewDesign = itemView.findViewById(R.id.bm_item_view);
        }
    }
}
