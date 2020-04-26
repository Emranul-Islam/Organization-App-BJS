package com.muhammad_sohag.biteshwor_jobo_somaj.custom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;

import com.muhammad_sohag.biteshwor_jobo_somaj.R;

public class LoadingDialog {

    private Activity activity;
    private AlertDialog alertDialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void showLoadingDialog (){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog_layout,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();

    }

   public void dismissLoadingDialog(){
        alertDialog.dismiss();
    }
}
