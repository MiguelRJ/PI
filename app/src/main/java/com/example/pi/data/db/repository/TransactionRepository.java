package com.example.pi.data.db.repository;

import com.example.pi.data.db.model.Transaction;

import java.util.ArrayList;
import java.util.Collections;

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
