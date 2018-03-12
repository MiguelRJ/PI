package com.mrj.morejmoney.ui.base;

import android.support.v4.app.Fragment;
import android.support.design.widget.Snackbar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      showMessage(String message)
 *      onError(String message)
 *      onError(int resId)
 */

public class BaseFragment extends Fragment {

    public void showMessage(String message){
        Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    public void onError(String message){
        Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    public void onError(int resId){
        Snackbar.make(getActivity().findViewById(android.R.id.content), getResources().getString(resId), Snackbar.LENGTH_SHORT).show();
    }
}
