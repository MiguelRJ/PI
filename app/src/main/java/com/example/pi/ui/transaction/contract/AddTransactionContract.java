package com.example.pi.ui.transaction.contract;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.data.model.Transaction;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.base.BaseView;

import java.util.ArrayList;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public interface AddTransactionContract {
    interface View extends BaseView {
        void showOnSuccess();
        void onAmountEmptyError();
        void showPiggyBankOnSpinner(ArrayList<PiggyBank> piggyBanks);
    }
    interface Presenter extends BasePresenter {
        void validateTransaction(Transaction transaction);
        void loadPiggyBank();
    }
}
