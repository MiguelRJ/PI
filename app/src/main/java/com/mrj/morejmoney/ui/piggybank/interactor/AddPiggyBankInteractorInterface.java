package com.mrj.morejmoney.ui.piggybank.interactor;

import com.mrj.morejmoney.data.model.PiggyBank;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      interface OnAddPiggyBankListener
 *      void validatePiggyBank(int id, int idUser, String name, AddPiggyBankInteractorInterface.OnAddPiggyBankListener listener);
 */

public interface AddPiggyBankInteractorInterface {
    interface OnAddPiggyBankListener {

        void onSucces();

        // Errores que puedan darse

        void onNameEmptyError();

        void onDuplicatedName();

    }
    void validatePiggyBank(PiggyBank piggyBank, AddPiggyBankInteractorInterface.OnAddPiggyBankListener listener);
}
