package com.example.pi.ui.piggybank.contract;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.base.BaseView;

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
    }

    interface Presenter extends BasePresenter {
        void loadPiggyBank();
        void onDeletedPiggyBank();
    }

}
