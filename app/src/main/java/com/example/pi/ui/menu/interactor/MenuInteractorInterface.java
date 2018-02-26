package com.example.pi.ui.menu.interactor;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.data.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 25/02/2018.
 */

public interface MenuInteractorInterface {

    interface OnLoadFinishedListener{
        void onSucces();
        void onError();
        void onDeleted();
    }

    void deletePiggyBank(PiggyBank piggyBank);
    void deleteTransaction(Transaction transaction);
    boolean existsAnyTransactionWithPiggyBankID(int id);
    void deleteAllTransactionsWithPiggyBankID(int id);
}
