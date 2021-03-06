package xyz.emranul.bijos.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import xyz.emranul.bijos.R;
import xyz.emranul.bijos.model.PeopleModel;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    private final Context context;
    private final Activity activity;
    private final List<PeopleModel> modelLit;
    private int lastPosition = 0;

    public PeopleAdapter(Context context, Activity activity, List<PeopleModel> modelLit) {
        this.context = context;
        this.activity = activity;
        this.modelLit = modelLit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //setup images
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.profile_ic);
        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(modelLit.get(position).getUrl())
                .into(holder.photo);
        holder.names.setText(modelLit.get(position).getName());
        holder.blood.setText(String.format("রক্তের গ্রুপঃ %s", modelLit.get(position).getBlood()));
        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {


                //set Alert dialog
                showNum(position);



            }
        });

        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullScreenImage(position);
            }
        });


        //Calling Animation
        setAnim(holder.itemView, position);
    }


    private void fullScreenImage(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View fullScreenView = LayoutInflater.from(context).inflate(R.layout.full_screeen_image_dialog, null);
        ImageView imageView = fullScreenView.findViewById(R.id.fullsc_iv);
        builder.setView(fullScreenView);

        Glide.with(context)
                .load(modelLit.get(position).getUrl())
                .into(imageView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    private void showNum(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View customLayout = LayoutInflater.from(context).inflate(R.layout.show_number_layout, null);
        TextView num1TV = customLayout.findViewById(R.id.sn_1);
        TextView num2TV = customLayout.findViewById(R.id.sn_2);

        num1TV.setText(modelLit.get(position).getNumber());
        num2TV.setText(modelLit.get(position).getNumber2());
        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        num1TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(modelLit.get(position).getNumber());
                Toast.makeText(context, "প্রথম নাম্বারে কল শুরু হচ্ছে....", Toast.LENGTH_SHORT).show();
            }
        });
        num2TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(modelLit.get(position).getNumber2());
                Toast.makeText(context, "দ্বিতীয় নাম্বারে কল শুরু হচ্ছে....", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @SuppressLint("MissingPermission")
    private void call( String number) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    //Making Animation
    private void setAnim(View view, int position) {
        if (position >= lastPosition) {
            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(800);
            view.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return modelLit.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView names, blood;
        ImageView callBtn;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.people_image_view);
            names = itemView.findViewById(R.id.people_text_view);
            blood = itemView.findViewById(R.id.people_blood_view);
            callBtn = itemView.findViewById(R.id.people_call_btn);
        }
    }
}
