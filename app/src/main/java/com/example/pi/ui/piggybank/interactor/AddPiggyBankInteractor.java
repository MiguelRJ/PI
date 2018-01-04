package com.example.pi.ui.piggybank.interactor;

import android.text.TextUtils;
import android.util.Log;

import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.data.db.repository.PiggyBankRepository;
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
        if (TextUtils.isEmpty(name)){
            listener.onNameEmptyError();
        } else if (true){ // UserRepository validate credentials User Pass
            int lastId = PiggyBankRepository.getInstance().getList();
            if (!PiggyBankRepository.getInstance().existsPiggyBankBy(name)) {
                if (id < 0) { //si es menor de 0 entonces esque es una Piggybank nueva
                    PiggyBankRepository.getInstance().addPiggyBank(new PiggyBank(lastId++, 0, name, calendar));
                } else {
                    PiggyBankRepository.getInstance().modPiggyBank(id,idUser,name,calendar);
                }
                listener.onSucces();
            } else {
                listener.onDuplicatedName();
            }

        }
    }
}
