package com.example.pi.ui.piggybank.interactor;

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
    void validatePiggyBank(int id, int idUser, String name, AddPiggyBankInteractorInterface.OnAddPiggyBankListener listener);
}
