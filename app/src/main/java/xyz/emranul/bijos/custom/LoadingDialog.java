package xyz.emranul.bijos.custom;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import xyz.emranul.bijos.R;

public class LoadingDialog {

    private final Activity activity;
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
