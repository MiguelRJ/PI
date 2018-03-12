package com.mrj.morejmoney.ui.piggybank.interactor;

import android.text.TextUtils;

import com.mrj.morejmoney.data.model.PiggyBank;
import com.mrj.morejmoney.data.repository.PiggyBankRepository;
import com.mrj.morejmoney.data.repository.UserRepository;
import com.mrj.morejmoney.data.prefs.AppPreferencesHelper;

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
