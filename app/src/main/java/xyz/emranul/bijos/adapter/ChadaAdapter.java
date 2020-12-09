package xyz.emranul.bijos.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

import xyz.emranul.bijos.R;
import xyz.emranul.bijos.model.ChadaModel;

public class ChadaAdapter extends RecyclerView.Adapter<ChadaAdapter.ViewHolder> {
    private final Context context;
    private final List<ChadaModel> chadaModelList;
    private int lastPosition = 0;

    public ChadaAdapter(Context context, List<ChadaModel> chadaModelList) {
        this.context = context;
        this.chadaModelList = chadaModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chada_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.chada_kromik_no.setText(chadaModelList.get(position).getKromik_no());

        //Format Date:
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(Long.parseLong(chadaModelList.get(position).getTime()));
        String time = DateFormat.format("dd-MM-yyyy hh:mm aa", cal).toString();

        holder.chada_time.setText(time);
        holder.chada_grahok.setText(chadaModelList.get(position).getGrahok());
        holder.chada_taka.setText(String.format("%s /=", chadaModelList.get(position).getTaka()));

        setAnim(holder.itemView, position);
    }

    //Simple Animation
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
        return chadaModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView chada_kromik_no;
        private final TextView chada_time;
        private final TextView chada_grahok;
        private final TextView chada_taka;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            chada_kromik_no = itemView.findViewById(R.id.chada_item_kromik_no);
            chada_time = itemView.findViewById(R.id.chada_item_date);
            chada_grahok = itemView.findViewById(R.id.chada_item_grahok_name);
            chada_taka = itemView.findViewById(R.id.chada_item_taka);
        }
    }
}
