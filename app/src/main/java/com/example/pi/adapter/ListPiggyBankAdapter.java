package com.example.pi.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pi.R;
import com.example.pi.data.model.PiggyBank;
import com.example.pi.data.repository.PiggyBankRepository;
import com.example.pi.ui.utils.AppConstants;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Miguel on 23/02/2018.
 */

public class ListPiggyBankAdapter extends RecyclerView.Adapter<ListPiggyBankAdapter.PiggyBankViewHolder> {


    private ArrayList<PiggyBank> piggyBanks;
    private static Context context; // necesito el contecto para tener acceso a los resources

    public ListPiggyBankAdapter(Context context) {
        piggyBanks = PiggyBankRepository.getInstance().getPiggybanks();
        this.context = context;
    }

    public ListPiggyBankAdapter orderByTotalAmount() {
        Collections.sort(piggyBanks, new PiggyBank.PiggyBankOrderByTotalAmount());
        return this;
    }

    public ListPiggyBankAdapter orderByCreationDate() {
        Collections.sort(piggyBanks, new PiggyBank.PiggyBankOrderByCreationDate());
        return this;
    }

    @Override
    public PiggyBankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        // 1.
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2.
        View view = inflater.inflate(R.layout.item_card_piggybank, null);

        // 3.
        PiggyBankViewHolder piggyBankViewHolder = new PiggyBankViewHolder(view);
        return piggyBankViewHolder;
    }

    @Override
    public void onBindViewHolder(PiggyBankViewHolder piggyBankViewHolder, int position) {
        piggyBankViewHolder.txvName.setText(piggyBanks.get(position).getName());
        piggyBankViewHolder.txvAmount.setText(AppConstants.decimalformat.format(piggyBanks.get(position).getTotalAmount()).replace(",", "."));
        Drawable d = context.getResources().getDrawable(R.drawable.shape_item_piggybank);
        piggyBankViewHolder.cl.setBackground(d);
        //piggyBankViewHolder.cl.setBackground(context.getResources().getDrawable(R.drawable.shape_loginedittext));
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

        public void bind(final PiggyBank piggyBank, final PiggyBankAdapter.OnItemClickListener listener) {
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
