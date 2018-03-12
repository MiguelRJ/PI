package com.mrj.morejmoney.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pi.R;
import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.repository.PiggyBankRepository;
import com.mrj.morejmoney.ui.utils.AppConstants;
import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

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

public class PiggyBankAdapter extends RecyclerView.Adapter<PiggyBankAdapter.PiggyBankViewHolder> {

    public interface OnItemClickListener{
        void OnItemClick(PiggyBank piggyBank);
        void OnItemLongClick(PiggyBank piggyBank);
    }
    private ArrayList<PiggyBank> piggyBanks;
    private static Context context; // necesito el contecto para tener acceso a los resources
    private OnItemClickListener listener;

    public PiggyBankAdapter(OnItemClickListener listener) {
        piggyBanks = PiggyBankRepository.getInstance().getPiggybanks();
        this.listener = listener;
    }

    public PiggyBankAdapter orderByTotalAmount() {
        Collections.sort(piggyBanks, new PiggyBank.PiggyBankOrderByTotalAmount());
        return this;
    }

    public PiggyBankAdapter orderByCreationDate() {
        Collections.sort(piggyBanks, new PiggyBank.PiggyBankOrderByCreationDate());
        return this;
    }

    @Override
    public PiggyBankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        // 1.
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2.
        View view = inflater.inflate(R.layout.item_piggybank, null);

        // 3.
        PiggyBankViewHolder piggyBankViewHolder = new PiggyBankViewHolder(view);
        return piggyBankViewHolder;
    }

    @Override
    public void onBindViewHolder(PiggyBankViewHolder piggyBankViewHolder, int position) {
        piggyBankViewHolder.icon.setLetter(piggyBanks.get(position).getName().substring(0,1));
        piggyBankViewHolder.txvName.setText(piggyBanks.get(position).getName());
        piggyBankViewHolder.txvAmount.setText(AppConstants.decimalformat.format(piggyBanks.get(position).getTotalAmount()).replace(",","."));
        Calendar calendar =  piggyBanks.get(position).getCreationDate();
        piggyBankViewHolder.txvCreationDate.setText(AppConstants.df.format(piggyBanks.get(position).getCreationDate().getTime()));
        piggyBankViewHolder.bind(piggyBanks.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return piggyBanks.size();
    }


    public static class PiggyBankViewHolder extends RecyclerView.ViewHolder {

        MaterialLetterIcon icon;
        TextView txvName;
        TextView txvAmount;
        TextView txvCreationDate;

        public PiggyBankViewHolder(View view) {
            super(view);
            icon = view.findViewById(R.id.icon);
            txvName = view.findViewById(R.id.txvName);
            txvAmount = view.findViewById(R.id.txvAmount);
            txvCreationDate = view.findViewById(R.id.txvCreationDate);
        }

        public void bind (final PiggyBank piggyBank, final PiggyBankAdapter.OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(piggyBank);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.OnItemLongClick(piggyBank);
                    return true;
                }
            });
        }
    }
}
