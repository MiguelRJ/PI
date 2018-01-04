package com.example.pi.ui.piggybank.contract;

import com.example.pi.ui.base.BasePresenter;
import com.example.pi.ui.base.BaseView;

import java.util.Calendar;

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

    }
    interface Presenter extends BasePresenter{
        void validatePiggyBank(int id, int idUser, String name);
    }
}
