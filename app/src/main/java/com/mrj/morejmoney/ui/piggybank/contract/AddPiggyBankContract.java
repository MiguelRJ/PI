package com.mrj.morejmoney.ui.piggybank.contract;

import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.ui.base.BasePresenter;
import com.mrj.morejmoney.ui.base.BaseView;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      interface view
 *      interface presenter
 *          void validatePiggyBank(int id, int idUser, String name);
 */

public interface AddPiggyBankContract {
    interface View extends BaseView{
        void showOnSucces();
        void showNameEmptyError();
        void showDuplicatedName();
    }
    interface Presenter extends BasePresenter{
        void validatePiggyBank(PiggyBank piggyBank);
    }
}
