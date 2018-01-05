package com.example.pi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pi.R;
import com.example.pi.data.db.model.Transaction;
import com.example.pi.data.db.repository.TransactionRepository;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

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

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    public interface OnItemClickListener{
        void OnItemClick(Transaction transaction);
        void OnItemLongClick(Transaction transaction);
    }
    private ArrayList<Transaction> transactions;
    private static Context context; // necesito el contecto para tener acceso a los resources
    private OnItemClickListener listener;

    public TransactionAdapter(OnItemClickListener listener) {
        transactions = TransactionRepository.getInstance().getTransactionsOrderByCreationDate();
        this.listener = listener;
    }

    public TransactionAdapter orderByAmount() {
        Collections.sort(transactions, new Transaction.TransactionOrderByAmount());
        return this;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        // 1.
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2.
        View view = inflater.inflate(R.layout.item_transaction, null);

        // 3.
        TransactionViewHolder transactionViewHolder = new TransactionViewHolder(view);
        return transactionViewHolder;
    }

    /**
     * Si la transaccion no tiene imagen sele asignara una en el momento de mostrar la lista
     * @return
     */
    public byte[] image() {
        Bitmap bitmap;
        bitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_transaction_default_diss));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata;
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder transactionViewHolder, int position) {
        byte[] bytes = transactions.get(position).getImage();
        if (bytes == null) {
            bytes = image();
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        transactionViewHolder.imageView.setImageBitmap(bitmap);
        transactionViewHolder.txvAmount.setText((String.valueOf(transactions.get(position).getAmount())));
        if (transactions.get(position).isPayment()) {
            transactionViewHolder.txvPayment.setText("payment");
        } else {
            transactionViewHolder.txvPayment.setText("deposit");
        }
        transactionViewHolder.txvAmount.setText(String.valueOf(transactions.get(position).getAmount()));
        Calendar calendar = transactions.get(position).getCreationDate();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        //String formattedDate = df.format(Calendar.getInstance().getTime());
        //String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        transactionViewHolder.txvCreationDate.setText(df.format(calendar.getTime()));
        transactionViewHolder.txvComment.setText(transactions.get(position).getComment());
        transactionViewHolder.txvEstablishment.setText(String.valueOf(transactions.get(position).getIdEstablishment()));
        transactionViewHolder.bind(transactions.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txvPayment;
        TextView txvAmount;
        TextView txvCreationDate;
        TextView txvComment;
        TextView txvEstablishment;

        public TransactionViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imgImage);
            txvPayment = view.findViewById(R.id.txvPayment);
            txvAmount = view.findViewById(R.id.txvAmount);
            txvCreationDate = view.findViewById(R.id.txvCreationDate);
            txvComment = view.findViewById(R.id.txvComment);
            txvEstablishment = view.findViewById(R.id.txvEstablishment);
        }

        public void bind (final Transaction transaction, final OnItemClickListener listener){
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
