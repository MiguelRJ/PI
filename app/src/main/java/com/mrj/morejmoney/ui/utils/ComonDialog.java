package com.mrj.morejmoney.ui.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.mrj.morejmoney.R;
import com.mrj.morejmoney.ui.base.BasePresenter;

/**
 * Created by Miguel on 07/01/2018.
 */

public class ComonDialog {

    public static String MESSAGE = "message";
    public static String TITTLE = "tittle";
    public static String TAG = "tag";

    public static Dialog showConfirmDialog(final Bundle bundle, Context context, final BasePresenter presenter, final int option){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(bundle.getString(ComonDialog.MESSAGE))
                .setTitle(bundle.getString(ComonDialog.TITTLE))
                .setPositiveButton(R.string.btnOk, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.options(option,bundle.getParcelable(bundle.getString(ComonDialog.TAG)));
                        dialogInterface.cancel();
                    }
                })
                .setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        return builder.create();
    }

}
