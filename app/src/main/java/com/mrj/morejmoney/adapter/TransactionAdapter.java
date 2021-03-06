package com.mrj.morejmoney.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrj.morejmoney.R;
import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.data.repository.PiggyBankRepository;
import com.mrj.morejmoney.data.repository.TransactionRepository;
import com.mrj.morejmoney.data.prefs.AppPreferencesHelper;
import com.mrj.morejmoney.ui.utils.AppConstants;

import java.io.ByteArrayOutputStream;
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

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private static Context context;

    public TransactionAdapter(@NonNull Context context) {
        super(context, R.layout.item_transaction, new ArrayList<Transaction>(TransactionRepository.getInstance().getTransactions()));
        this.context = context;
    }

    public TransactionAdapter orderByAmountDESC() {
        sort(new Transaction.TransactionOrderByAmountDESC());
        return this;
    }
    public TransactionAdapter orderByAmountASC() {
        sort(new Transaction.TransactionOrderByAmountASC());
        return this;
    }
    public TransactionAdapter orderByCreationDateDESC() {
        sort(new Transaction.TransactionOrderByCreationDateDESC());
        return this;
    }
    public TransactionAdapter orderByCreationDateASC() {
        sort(new Transaction.TransactionOrderByCreationDateASC());
        return this;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TransactionHolder transactionHolder;
        View view = convertView;


        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_transaction, null);
            transactionHolder = new TransactionHolder();
            transactionHolder.imageView = view.findViewById(R.id.imgImage);
            transactionHolder.txvPayment = view.findViewById(R.id.txvPayment);
            transactionHolder.txvAmount = view.findViewById(R.id.txvAmount);
            transactionHolder.txvCreationDate = view.findViewById(R.id.txvCreationDate);
            transactionHolder.txvComment = view.findViewById(R.id.txvComment);
            transactionHolder.txvPiggyBank = view.findViewById(R.id.txvPiggyBank);
            view.setTag(transactionHolder);
        } else {
            transactionHolder = (TransactionHolder) view.getTag();
        }
        byte[] bytes = getItem(position).getImage();
        if (AppPreferencesHelper.getInstance().getIconShow()) {
            if (bytes == null) {
                bytes = image();
            }
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            transactionHolder.imageView.setImageBitmap(bitmap);
        } else {
            transactionHolder.imageView.setImageBitmap(null);
            transactionHolder.imageView.setVisibility(View.GONE);
        }
        transactionHolder.txvAmount.setText(AppConstants.decimalformat.format(getItem(position).getAmount()).replace(",","."));
        if (getItem(position).isPayment()) {
            transactionHolder.txvPayment.setText(context.getString(R.string.Payment));
        } else {
            transactionHolder.txvPayment.setText(context.getString(R.string.Deposit));
        }
        Calendar calendar = getItem(position).getCreationDate();
        transactionHolder.txvCreationDate.setText(AppConstants.df.format(calendar.getTime()));
        transactionHolder.txvComment.setText(getItem(position).getComment());
        transactionHolder.txvPiggyBank.setText(
                PiggyBankRepository.getInstance().loadPiggyBank(
                        getItem(position).getIdPiggyBank()
                ).getName()
        );

        return view;
    }

    /**
     * Si la transaccion no tiene imagen sele asignara una en el momento de mostrar la lista
     * @return
     */
    public byte[] image() {
        Bitmap bitmap;
        bitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(context.getResources(), AppPreferencesHelper.getInstance().getIcon()));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata;
    }

    class TransactionHolder {
        ImageView imageView;
        TextView txvPayment;
        TextView txvAmount;
        TextView txvCreationDate;
        TextView txvComment;
        TextView txvPiggyBank;
    }

}
