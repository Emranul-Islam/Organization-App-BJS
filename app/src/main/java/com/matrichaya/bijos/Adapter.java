package com.matrichaya.bijos;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter  extends ArrayAdapter {

    List list = new ArrayList();
    public Adapter(Context context, int resource) {
        super(context, resource);

    }

   static class DataHandler {
        CircleImageView photo;
      //  ImageView photo;
        TextView title;
        ImageView telePhone;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View row;
        row = convertView;
        final DataProvider dataProvider = (DataProvider) this.getItem(position);;

        DataHandler handler;
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.contact_item, parent, false);
            handler = new DataHandler();
            handler.photo = row.findViewById(R.id.ciViewID);
        //    handler.photo = (ImageView) row.findViewById(R.id.iViewID);
            handler.title = (TextView) row.findViewById(R.id.tViewNameID);
            handler.telePhone = (ImageView) row.findViewById(R.id.callButtonID);
        } else {
            handler = (DataHandler) row.getTag();
        }

        handler.photo.setImageResource(dataProvider.getPhoto());
        handler.title.setText(dataProvider.getTitle());
//        handler.telePhone.setText(dataProvider.getTelePhone());
        handler.telePhone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + dataProvider.getTelePhone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        row.setTag(handler);
        return row;
    }
}
