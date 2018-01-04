package com.example.pi.ui.piggybank.interactor;

/**
 * Created by Miguel on 04/01/2018.
 */

public class AddPiggyBankInteractor implements AddPiggyBankInteractorInterface {

    @Override
    public void validatePiggyBank(int id, int idUser, String name, OnAddPiggyBankListener listener) {

        listener.onSucces();
        
    }

}
