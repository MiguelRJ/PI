package com.example.pi.data.db.repository;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.pi.R;
import com.example.pi.data.db.model.Transaction;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *      ArrayList<Transaction>
 *      transactionRepository
 *      Constructor
 *      initialize
 *      getInstance() of Repository
 *      addTransaction()
 *      getTransactions()
 *      getTransactionsOrderByCreationDate()
 *      getTransactionsOrderByAmount()
 */

public class TransactionRepository {

    private ArrayList<Transaction> transactions;
    private static TransactionRepository transactionRepository;

    static{
        transactionRepository = new TransactionRepository();
    }

    public TransactionRepository(){
        transactions = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        addTransaction(new Transaction(0,0,0,1,true,50,new GregorianCalendar(2017, 11-1,15,17,30,00),"100 del suelo",36.719116, -4.453754,null));
        addTransaction(new Transaction(1,0,0,2,true,75,"1234567890",36.719116, -4.453754,null));
        addTransaction(new Transaction(2,0,1,3,false,100,new GregorianCalendar(2017,11-1,16,17,30,00),"100 del suelo",36.719116, -4.453754,null));
        addTransaction(new Transaction(3,0,1,4,true,25,"12345678901234567890",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(4,0,0,5,false,150,new GregorianCalendar(2017,11-1,17,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(5,0,1,6,false,125,"123456789012345678901234567890",36.719116, -4.453754,new byte[]{1,2,3,4,5}));

        addTransaction(new Transaction(0,1,0,6,true,50,new GregorianCalendar(2017,11-1,15,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(1,1,1,5,true,75,"1234567890",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(2,1,1,4,false,100,new GregorianCalendar(2017,11-1,16,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(3,1,0,3,true,25,"12345678901234567890",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(4,1,1,2,false,150,new GregorianCalendar(2017,11-1,17,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(5,1,0,1,false,125,"123456789012345678901234567890",36.719116, -4.453754,new byte[]{1,2,3,4,5}));

    }

    /* GET INSTANCE OF REPOSITORY */
    public static TransactionRepository getInstance(){
        return transactionRepository;
    }

    /* ADD TRANCASTION */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /* GET TRANSACTIONS ORDER BY */
    public ArrayList<Transaction> getTransactions(){
        Collections.sort(transactions);
        return  transactions;
    }

    public ArrayList<Transaction> getTransactionsOrderByCreationDate(){
        Collections.sort(transactions);
        return  transactions;
    }

    public ArrayList<Transaction> getTransactionsOrderByAmount(){
        Collections.sort(transactions, new Transaction.TransactionOrderByAmount());
        return  transactions;
    }
}
