package com.example.pi.ui.transaction.contract;

import com.example.pi.data.db.model.Transaction;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.base.BaseView;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 05/01/18
 *
 */

public interface ListTransactionContract {
    interface View extends BaseView {
        void showTransaction(List<Transaction> list);
    }
    interface Presenter extends BasePresenter {
        void loadTransaction();
    }
}
