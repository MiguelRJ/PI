package com.example.pi.ui.piggybank.interactor;

import android.content.pm.PermissionGroupInfo;

import com.example.pi.data.model.PiggyBank;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
