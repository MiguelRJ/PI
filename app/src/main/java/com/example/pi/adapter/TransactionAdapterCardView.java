package com.example.pi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pi.R;
import com.example.pi.data.model.Transaction;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.data.repository.PiggyBankRepository;
import com.example.pi.data.repository.TransactionRepository;
import com.example.pi.ui.utils.AppConstants;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by
 *
 * @author Miguel Rodriguez Jimenez
 * @date 18/11/17
 * Constructor
 * getView()
 * orderByTotalAmount()
 * orderByCreationDate()
 * TransactionHolder{}
 */

public class TransactionAdapterCardView extends RecyclerView.Adapter<TransactionAdapterCardView.TransactionViewHolder> {

    private static Context context;
    private ArrayList<Transaction> transactions;

    public TransactionAdapterCardView(Context context) {
        this.transactions = TransactionRepository.getInstance().getTransactionsOrderByCreationDate(10);
        this.context = context;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_card_transaction, null);

        TransactionViewHolder transactionViewHolder = new TransactionViewHolder(view);
        return transactionViewHolder;
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder transactionHolder, int position) {
        transactionHolder.txvAmount.setText(AppConstants.decimalformat.format(transactions.get(position).getAmount()).replace(",","."));
        if (transactions.get(position).isPayment()) {
            transactionHolder.txvPayment.setText(context.getString(R.string.Payment));
        } else {
            transactionHolder.txvPayment.setText(context.getString(R.string.Deposit));
        }
        Calendar calendar = transactions.get(position).getCreationDate();
        transactionHolder.txvCreationDate.setText(AppConstants.df.format(calendar.getTime()));
        transactionHolder.txvComment.setText(transactions.get(position).getComment());
        transactionHolder.txvPiggyBank.setText(
                PiggyBankRepository.getInstance().loadPiggyBank(
                        transactions.get(position).getIdPiggyBank()
                ).getName()
        );
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }


    public static class TransactionViewHolder extends RecyclerView.ViewHolder {

        TextView txvPayment;
        TextView txvAmount;
        TextView txvCreationDate;
        TextView txvComment;
        TextView txvPiggyBank;

        public TransactionViewHolder(View view) {
            super(view);
            txvPayment = view.findViewById(R.id.txvPayment);
            txvAmount = view.findViewById(R.id.txvAmount);
            txvCreationDate = view.findViewById(R.id.txvCreationDate);
            txvComment = view.findViewById(R.id.txvComment);
            txvPiggyBank = view.findViewById(R.id.txvPiggyBank);
        }

    }

}
