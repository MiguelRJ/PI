package com.example.pi.ui.transaction.contract;

import com.example.pi.data.model.Transaction;
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
        Transaction getTransaction(Integer position);
        String nameSelecteds();
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
