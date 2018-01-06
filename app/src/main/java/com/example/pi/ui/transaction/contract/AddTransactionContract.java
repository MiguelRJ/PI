package com.example.pi.ui.transaction.contract;

import com.example.pi.data.db.model.Transaction;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.base.BaseView;

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
    }
    interface Presenter extends BasePresenter {
        void validateTransaction(Transaction transaction);
    }
}
