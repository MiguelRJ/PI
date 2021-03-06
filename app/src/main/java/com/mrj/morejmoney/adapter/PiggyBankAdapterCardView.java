package com.mrj.morejmoney.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrj.morejmoney.R;
import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.repository.PiggyBankRepository;
import com.mrj.morejmoney.ui.utils.AppConstants;

import java.util.ArrayList;

/**
 * Created by Miguel on 23/02/2018.
 */

public class PiggyBankAdapterCardView extends RecyclerView.Adapter<PiggyBankAdapterCardView.PiggyBankViewHolder> {

    public interface OnItemClickListener{
        void OnItemClick(PiggyBank piggyBank);
        void OnItemLongClick(PiggyBank piggyBank);
    }
    private OnItemClickListener listener;
    private ArrayList<PiggyBank> piggyBanks;
    private static Context context; // necesito el contecto para tener acceso a los resources

    public PiggyBankAdapterCardView(Context context, OnItemClickListener listener) {
        this.piggyBanks = PiggyBankRepository.getInstance().getPiggybanks();
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PiggyBankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_card_piggybank, null);

        PiggyBankViewHolder piggyBankViewHolder = new PiggyBankViewHolder(view);
        return piggyBankViewHolder;
    }

    @Override
    public void onBindViewHolder(PiggyBankViewHolder piggyBankViewHolder, int position) {
        piggyBankViewHolder.txvName.setText(piggyBanks.get(position).getName());
        piggyBankViewHolder.txvAmount.setText(AppConstants.decimalformat.format(piggyBanks.get(position).getTotalAmount()).replace(",", "."));
        Drawable d = context.getResources().getDrawable(R.drawable.shape_item_piggybank);
        piggyBankViewHolder.cl.setBackground(d);
        piggyBankViewHolder.bind(piggyBanks.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return piggyBanks.size();
    }


    public static class PiggyBankViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout cl;
        TextView txvName;
        TextView txvAmount;

        public PiggyBankViewHolder(View view) {
            super(view);
            cl = view.findViewById(R.id.cl);
            txvName = view.findViewById(R.id.txvName);
            txvAmount = view.findViewById(R.id.txvAmount);
        }

        public void bind (final PiggyBank piggyBank, final PiggyBankAdapterCardView.OnItemClickListener listener) {
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
