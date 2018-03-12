package com.mrj.morejmoney.ui.menu.contract;

import com.mrj.morejmoney.ui.base.BasePresenter;
import com.mrj.morejmoney.ui.base.BaseView;

/**
 * Created by Miguel on 25/02/2018.
 */

public interface MenuContract {
    interface View extends BaseView {
        void onDeleted();
        void onSuccess();
        void onError();
    }
    interface Presenter extends BasePresenter {
        boolean existsAnyTransactionWithPiggyBankID(int id);
    }
}
