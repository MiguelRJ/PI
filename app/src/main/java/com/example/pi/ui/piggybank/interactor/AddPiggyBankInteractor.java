package com.example.pi.ui.piggybank.interactor;

import android.text.TextUtils;
import android.util.Log;

import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.data.db.repository.PiggyBankRepository;
import com.example.pi.data.db.repository.UserRepository;
import com.example.pi.data.prefs.AppPreferencesHelper;
import com.example.pi.ui.utils.AppConstants;

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
    public void validatePiggyBank(int id, int idUser, String name, GregorianCalendar calendar, OnAddPiggyBankListener listener) {
        Log.e("id",String.valueOf(id));
        Log.e("iduser",String.valueOf(idUser));
        Log.e("name",name);
        if (TextUtils.isEmpty(name)){
            listener.onNameEmptyError();
        } else if (UserRepository.getInstance().validateCredentials(
                AppPreferencesHelper.getInstance().getCurrentUserName(),
                AppPreferencesHelper.getInstance().getCurrentUserPassword())){ // UserRepository validate credentials User Pass

            if (id < 0) { //si es menor de 0 entonces esque es una Piggybank nueva

                if (PiggyBankRepository.getInstance().existsPiggyBankBy(name)) {
                    listener.onDuplicatedName();
                } else {
                    int lastId = PiggyBankRepository.getInstance().getLastId();
                    PiggyBankRepository.getInstance().addPiggyBank(new PiggyBank(lastId+1, Integer.parseInt(String.valueOf(AppPreferencesHelper.getInstance().getCurrentUserId())), name, calendar));
                    listener.onSucces();
                }

            } else {

                if (PiggyBankRepository.getInstance().existsPiggyBankBy(name,calendar)) {
                    listener.onDuplicatedName();
                } else {
                    PiggyBankRepository.getInstance().modPiggyBank(id,idUser,name,calendar);
                    listener.onSucces();
                }

            }
        }
    }
}
