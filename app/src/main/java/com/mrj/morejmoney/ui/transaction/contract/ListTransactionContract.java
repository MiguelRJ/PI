package com.mrj.morejmoney.ui.transaction.contract;

import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.ui.base.BasePresenter;
import com.mrj.morejmoney.ui.base.BaseView;

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
        Transaction getTransaction(Integer position);
        String nameSelecteds();
        void showProgress();
        void dismissProgress();
    }
    interface Presenter extends BasePresenter {
        void loadTransaction();
        void setNewSelection(int position);
        void removeSelection(int position);
        void deleteSelection();
        void clearSelection();
        boolean isPositionChecked(int position);
        String nameSelecteds();
    }
}
