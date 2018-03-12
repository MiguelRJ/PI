package com.mrj.morejmoney.ui.piggybank.contract;

import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.ui.base.BasePresenter;
import com.mrj.morejmoney.ui.base.BaseView;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      interface View extends BaseView
 *          void showPiggyBank
 *          PiggyBank getPiggyBank
 *
 *      interface Presenter
 *          loadPiggyBank()
 */

public interface ListPiggyBankContract {

    interface View extends BaseView {
        void showPiggyBank(List<PiggyBank> list);
        void onDeletedPiggyBank();
        void showProgress();
        void dismissProgress();
        void onSuccess();
        void onError();
    }

    interface Presenter extends BasePresenter {
        void loadPiggyBank();
        void onDeletedPiggyBank();
        boolean existsAnyTransactionWithPiggyBankID(int id);
    }

}
