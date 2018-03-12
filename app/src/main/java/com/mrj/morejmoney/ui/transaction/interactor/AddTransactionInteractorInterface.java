package com.mrj.morejmoney.ui.transaction.interactor;

import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.model.Transaction;

import java.util.ArrayList;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public interface AddTransactionInteractorInterface {

    interface OnAddTransactionListener{
        void onSuccess();
        void onAmountEmptyError();
        void showPiggyBankOnSpinner(ArrayList<PiggyBank> piggyBanks);
    }

    void validateTransaction(Transaction transaction, AddTransactionInteractorInterface.OnAddTransactionListener listener);
    void loadPiggyBank(AddTransactionInteractorInterface.OnAddTransactionListener listener);
}
