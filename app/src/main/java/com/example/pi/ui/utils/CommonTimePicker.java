package com.example.pi.ui.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public class CommonTimePicker extends DialogFragment {

    public static final String TAG = "CommonTimePicker";

    OnTimeSetListener ontimeSet;
    private int hour, minute;

    public CommonTimePicker() {}

    public void setCallBack(OnTimeSetListener ontime) {
        ontimeSet = ontime;
    }

    @SuppressLint("NewApi")
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        hour = args.getInt("hour");
        minute = args.getInt("minute");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(),ontimeSet, hour,minute,true);
    }
}
