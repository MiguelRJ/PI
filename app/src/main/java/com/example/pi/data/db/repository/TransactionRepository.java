package com.example.pi.data.db.repository;

import com.example.pi.data.db.model.Transaction;

import java.util.ArrayList;
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
        addTransaction(new Transaction(0,0,0,0,true,50,new GregorianCalendar(2017,11,15,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(1,0,0,0,true,75,"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(2,0,1,0,false,100,new GregorianCalendar(2017,11,16,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(3,0,1,0,true,25,"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(4,0,0,0,false,150,new GregorianCalendar(2017,11,17,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(5,0,1,0,false,125,"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));

        addTransaction(new Transaction(0,1,0,0,true,50,new GregorianCalendar(2017,11,15,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(1,1,1,0,true,75,"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(2,1,1,0,false,100,new GregorianCalendar(2017,11,16,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(3,1,0,0,true,25,"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(4,1,1,0,false,150,new GregorianCalendar(2017,11,17,17,30,00),"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));
        addTransaction(new Transaction(5,1,0,0,false,125,"100 del suelo",36.719116, -4.453754,new byte[]{1,2,3,4,5}));

    }

    /* GET INSTANCE OF REPOSITORY */
    public TransactionRepository getInstance(){
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
        Collections.sort(transactions, new Transaction.TransactionOrderByCreationDate());
        return  transactions;
    }
}
