package com.example.pi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pi.R;
import com.example.pi.data.db.model.Transaction;
import com.example.pi.data.db.repository.TransactionRepository;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 18/11/17
 *      Constructor
 *      getView()
 *      orderByTotalAmount()
 *      orderByCreationDate()
 *      TransactionHolder{}
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private ArrayList<Transaction> transactions;
    private static Context context;

    public TransactionAdapter(){
        transactions = TransactionRepository.getInstance().getTransactions();

    }

    public TransactionAdapter orderByAmount(){
        Collections.sort(transactions,new Transaction.TransactionOrderByAmount());
        return this;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        // 1.
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2.
        View view = inflater.inflate(R.layout.item_transaction,null);

        // 3.
        TransactionViewHolder transactionViewHolder = new TransactionViewHolder(view);
        return transactionViewHolder;
    }

    public byte[] image(){
        Bitmap bitmap;
        //Drawable drawable = ContextCompat.getDrawable(context,R.drawable.ic_launcher_background);
        //Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.outHeight = 48;
        o.outWidth = 48;
        //Bitmap bitmap = BitmapFactory.decodeFile(context.getResources().getResourceName(R.drawable.ic_launcher_background));
        //Bitmap bitmap = BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.ic_launcher_background,o);

        //int id = R.drawable.ic_launcher_background;
        //Bitmap b = BitmapFactory.decodeResource(Resources.getSystem(),R.drawable.ic_launcher_background,o);
        //Drawable is = ContextCompat.getDrawable(context,R.drawable.ic_launcher_background);
        //is.setFilterBitmap(true);
        bitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_transaction_default));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata;
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder transactionViewHolder, int position) {
        byte[] bytes = transactions.get(position).getImage();
        if (bytes == null){
            bytes = image();
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        transactionViewHolder.imageView.setImageBitmap(bitmap);

        transactionViewHolder.txvAmount.setText((String.valueOf(transactions.get(position).getAmount())));

        if(transactions.get(position).isPayment()){
            transactionViewHolder.txvPayment.setText("pago");
        } else {
            transactionViewHolder.txvPayment.setText("ingreso");
        }

        transactionViewHolder.txvAmount.setText(String.valueOf(transactions.get(position).getAmount()));

        Calendar calendar =  transactions.get(position).getCreationDate();
        String date = calendar.get(Calendar.DAY_OF_MONTH)+"/"+ calendar.get(Calendar.MONTH)+"/"+ calendar.get(Calendar.YEAR);
        transactionViewHolder.txvCreationDate.setText(date);

        transactionViewHolder.txvComment.setText(transactions.get(position).getComment());

        transactionViewHolder.txvEstablishment.setText(String.valueOf(transactions.get(position).getIdEstablishment()));
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class TransactionViewHolder extends  RecyclerView.ViewHolder {

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
    }
}
