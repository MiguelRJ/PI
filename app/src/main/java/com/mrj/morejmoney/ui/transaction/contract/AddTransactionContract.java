package com.mrj.morejmoney.ui.transaction.contract;

import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.ui.base.BasePresenter;
import com.mrj.morejmoney.ui.base.BaseView;

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
