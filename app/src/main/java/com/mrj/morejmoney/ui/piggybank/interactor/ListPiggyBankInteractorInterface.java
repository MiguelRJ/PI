package com.mrj.morejmoney.ui.piggybank.interactor;

import com.mrj.morejmoney.data.model.PiggyBank;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      interface OnLoadFinishedListener
 *          onSucces(List<PiggyBank> list)
 *      loadPiggyBank()
 */

public interface ListPiggyBankInteractorInterface {

    interface OnLoadFinishedListener{
        void onSucces(List<PiggyBank> list);
        void onSucces();
        void onError();
        void onDeletedPiggyBank();
        void showProgress();
        void dismissProgress();
    }

    void loadPiggyBank(); // Get the list of pojos
    void deletePiggyBank(PiggyBank piggyBank);
    boolean existsAnyTransactionWithPiggyBankID(int id);
    void deleteAllTransactionsWithPiggyBankID(int id);
}
