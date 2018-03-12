package com.mrj.morejmoney.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrj.morejmoney.R;
import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.data.repository.PiggyBankRepository;
import com.mrj.morejmoney.data.repository.TransactionRepository;
import com.mrj.morejmoney.ui.utils.AppConstants;

import java.util.ArrayList;
import java.util.Calendar;

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

    public interface OnItemClickListener{
        void OnItemClick(Transaction transaction);
        void OnItemLongClick(Transaction transaction);
    }
    private OnItemClickListener listener;
    private static Context context;
    private ArrayList<Transaction> transactions;

    public TransactionAdapterCardView(Context context, OnItemClickListener listener) {
        this.transactions = TransactionRepository.getInstance().getTransactionsOrderByCreationDate(10);
        this.context = context;
        this.listener = listener;
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
        transactionHolder.bind(transactions.get(position), listener);
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

        public void bind (final Transaction transaction, final TransactionAdapterCardView.OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(transaction);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.OnItemLongClick(transaction);
                    return true;
                }
            });
        }

    }

}
