package com.mrj.morejmoney.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mrj.morejmoney.R;
import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.ui.utils.AppConstants;

import java.util.ArrayList;

/**
 * Created by Miguel on 25/02/2018.
 */

public class PiggyBankAdapterSpinner extends ArrayAdapter<PiggyBank> {

    private static Context context;

    public PiggyBankAdapterSpinner(@NonNull Context context) {
        super(context, R.layout.item_piggybank_spinner, R.id.txvName, new ArrayList<PiggyBank>());
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        PiggyBankSpinnerHolder holder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_piggybank_spinner, null);
            holder = new PiggyBankSpinnerHolder();
            holder.txvName = view.findViewById(R.id.txvName);
            holder.txvDate = view.findViewById(R.id.txvDate);
            view.setTag(holder);
        } else {
            holder = (PiggyBankSpinnerHolder) view.getTag();
        }

        holder.txvName.setText(getItem(position).getName());
        holder.txvDate.setText(AppConstants.df.format(getItem(position).getCreationDate().getTime()));
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        PiggyBankSpinnerHolder holder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_piggybank_spinner, null);
            holder = new PiggyBankSpinnerHolder();
            holder.txvName = view.findViewById(R.id.txvName);
            holder.txvDate = view.findViewById(R.id.txvDate);
            view.setTag(holder);
        } else {
            holder = (PiggyBankSpinnerHolder) view.getTag();
        }

        holder.txvName.setText(getItem(position).getName());
        holder.txvDate.setText(AppConstants.df.format(getItem(position).getCreationDate().getTime()));
        return view;
    }

    class PiggyBankSpinnerHolder{
        TextView txvName, txvDate;
    }
}
