package com.example.pi.data.repository;

import android.util.Log;

import com.example.pi.data.base.TransactionDaoBase;
import com.example.pi.data.db.PIContract;
import com.example.pi.data.db.dao.TransactionDao;
import com.example.pi.data.model.Transaction;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.ui.menu.interactor.MenuInteractorInterface;
import com.example.pi.ui.piggybank.interactor.ListPiggyBankInteractorInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;

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

    private TransactionDaoBase dao;
    private static TransactionRepository transactionRepository;

    static{
        transactionRepository = new TransactionRepository();
    }

    public TransactionRepository(){
        dao = new TransactionDao();
        //initialize();
    }

    /* GET INSTANCE OF REPOSITORY */
    public static TransactionRepository getInstance(){
        return transactionRepository;
    }

    public void add(Transaction transaction){
        long id = dao.add(transaction);
        if (id == -1){
            // error
        } else {
            // success
        }
    }

    public void udpate(Transaction transaction){
        int id = dao.update(transaction);
        if (id == -1){
            // error
        } else {
            // success
        }
    }

    public void delete(Transaction transaction){
        int id = dao.delete(transaction);
        if (id == 0){
            // error
        } else {
            // success
        }
    }

    public boolean exists(int id){
        return dao.exists(id);
    }

    public void deleteAll(int id, ListPiggyBankInteractorInterface.OnLoadFinishedListener listener){
        dao.deleteAll(id);
        if (id == 0){
            listener.onError();
        } else {
            listener.onSucces();
        }
    }

    public void deleteAll(int id, MenuInteractorInterface.OnLoadFinishedListener listener){
        dao.deleteAll(id);
        if (id == 0){
            listener.onError();
        } else {
            listener.onSucces();
        }
    }

    public double sumTotalAmount(int idPiggyBank){
        return dao.sumTotalAmount(idPiggyBank);
    }

    /* GET TRANSACTIONS ORDER BY */
    public ArrayList<Transaction> getTransactions(){
        return dao.loadAll(PIContract.TransactionEntry.DEFAULT_SORT,0,true);
    }

    /**
     *
     * @param limit obtiene las x primeras
     * @return
     */
    public ArrayList<Transaction> getTransactionsOrderByCreationDate(int limit){
        return dao.loadAll(PIContract.TransactionEntry.COL_DATE,limit, true);
    }

    /**
     *
     * @param limit obtiene las x primeras
     * @return
     */
    public ArrayList<Transaction> getTransactionsOrderByAmount(int limit){
        return dao.loadAll(PIContract.TransactionEntry.COL_AMOUNT,limit, true);
    }

}
