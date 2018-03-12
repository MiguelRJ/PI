package com.mrj.morejmoney.ui.menu.interactor;

import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.model.Transaction;

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
