package com.muhammad_sohag.biteshwor_jobo_somaj.adapter;

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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.muhammad_sohag.biteshwor_jobo_somaj.R;
import com.muhammad_sohag.biteshwor_jobo_somaj.model.BloodModel;
import com.muhammad_sohag.biteshwor_jobo_somaj.model.PeopleModel;

import java.util.List;

public class BloodAdapter extends RecyclerView.Adapter<BloodAdapter.ViewHolder> {
    private Context context;
    private List<BloodModel> bloodModels;

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
        String elaka = bloodModels.get(position).getElaka();
        holder.elaka.setText("এলাকা: "+elaka);
        switch (elaka){
            case "বিটেশ্বর":
                holder.view.setBackgroundColor(Color.RED);
                break;
            case "মাদলা":
                holder.view.setBackgroundColor(Color.GREEN);
                break;
            case "মলয়":
                holder.view.setBackgroundColor(Color.CYAN);
                break;
            case "বরকোটা":
                holder.view.setBackgroundColor(Color.MAGENTA);
                break;
            default:
                holder.view.setBackgroundColor(Color.BLACK);
        }

    }

    @Override
    public int getItemCount() {
        return bloodModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, elaka;
        private ImageView callBtn;
        private View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bm_item_name);
            elaka = itemView.findViewById(R.id.bm_item_elaka);
            callBtn = itemView.findViewById(R.id.bm_item_call);
            view = itemView.findViewById(R.id.bm_item_view);
        }
    }
}
