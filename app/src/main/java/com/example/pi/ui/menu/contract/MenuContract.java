package com.example.pi.ui.menu.contract;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.base.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 25/02/2018.
 */

public interface MenuContract {
    interface View extends BaseView {
        void onDeletedPiggyBank();
        void onSuccess();
        void onError();
    }
    interface Presenter extends BasePresenter {
        void onDeletedPiggyBank();
        boolean existsAnyTransactionWithPiggyBankID(int id);
    }
}
