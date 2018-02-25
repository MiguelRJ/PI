package com.example.pi.ui.piggybank.interactor;

import android.text.TextUtils;
import android.util.Log;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.data.repository.PiggyBankRepository;
import com.example.pi.data.repository.UserRepository;
import com.example.pi.data.prefs.AppPreferencesHelper;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *
 *
 */

public class AddPiggyBankInteractor implements AddPiggyBankInteractorInterface {

    @Override
    public void validatePiggyBank(PiggyBank piggyBank, OnAddPiggyBankListener listener) {
        if (TextUtils.isEmpty(piggyBank.getName().replace(" ",""))){
            listener.onNameEmptyError();
        } else if (UserRepository.getInstance().validateCredentials(
                AppPreferencesHelper.getInstance().getCurrentUserName(),
                AppPreferencesHelper.getInstance().getCurrentUserPassword())){ // UserRepository validate credentials User Pass

            if (piggyBank.getId() < 0) { //si es menor de 0 entonces esque es una Piggybank nueva

                /*if (PiggyBankRepository.getInstance().existsPiggyBankBy(name)) {
                    listener.onDuplicatedName();
                } else {
                    int lastId = PiggyBankRepository.getInstance().getLastId();
                    PiggyBankRepository.getInstance().add(new PiggyBank(lastId+1, Integer.parseInt(String.valueOf(AppPreferencesHelper.getInstance().getCurrentUserId())), name, calendar));
                    listener.onSucces();
                }*/
                PiggyBankRepository.getInstance().add(piggyBank);
                listener.onSucces();
            } else {

                /*if (PiggyBankRepository.getInstance().existsPiggyBankBy(name,calendar)) {
                    listener.onDuplicatedName();
                } else {
                    PiggyBankRepository.getInstance().udpate(new PiggyBank(id,idUser,name,calendar));
                    listener.onSucces();
                }*/
                PiggyBankRepository.getInstance().udpate(piggyBank);
                listener.onSucces();
            }
        }
    }
}
