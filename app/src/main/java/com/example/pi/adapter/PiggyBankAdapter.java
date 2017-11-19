package com.example.pi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pi.R;
import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.data.db.repository.PiggyBankRepository;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *      Constructor
 *      getView()
 *      orderByTotalAmount()
 *      orderByCreationDate()
 *      PiggyBankHolder{}
 */

public class PiggyBankAdapter extends ArrayAdapter<PiggyBank> {

    public PiggyBankAdapter(@NonNull Context context) {
        super(context, R.layout.item_piggybank, new ArrayList<>(PiggyBankRepository.getInstance().getPiggybanks()));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PiggyBankHolder piggyBankHolder;
        View view = convertView;
        String date;
        Calendar calendar;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_piggybank,null);
            piggyBankHolder = new PiggyBankHolder();

            piggyBankHolder.icon = view.findViewById(R.id.icon);
            piggyBankHolder.txvName = view.findViewById(R.id.txvName);
            piggyBankHolder.txvAmount = view.findViewById(R.id.txvAmount);
            piggyBankHolder.txvCreationDate = view.findViewById(R.id.txvCreationDate);
            view.setTag(piggyBankHolder);
        } else {
            piggyBankHolder = (PiggyBankHolder) view.getTag();
        }

        piggyBankHolder.icon.setLetter(getItem(position).getName().substring(0,1));
        piggyBankHolder.txvName.setText(getItem(position).getName());
        piggyBankHolder.txvAmount.setText((String.valueOf(getItem(position).getTotalAmount())));
        calendar =  getItem(position).getCreationDate();
        //date = calendar.get(Calendar.DAY_OF_MONTH)+"/"+ calendar.get(Calendar.MONTH)+"/"+ calendar.get(Calendar.YEAR);
        piggyBankHolder.txvCreationDate.setText(df.format(getItem(position).getCreationDate().getTime()));
        return view;
    }

    public PiggyBankAdapter orderByTotalAmount(){
        sort(new PiggyBank.PiggyBankOrderByTotalAmount());
        return this;
    }

    public PiggyBankAdapter orderByCreationDate(){
        sort(new PiggyBank.PiggyBankOrderByCreationDate());
        return this;
    }

    class PiggyBankHolder{
        MaterialLetterIcon icon;
        TextView txvName;
        TextView txvAmount;
        TextView txvCreationDate;
    }
}
