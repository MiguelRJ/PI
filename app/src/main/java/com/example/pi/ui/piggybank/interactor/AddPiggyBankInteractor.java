package com.example.pi.ui.piggybank.interactor;

import android.text.TextUtils;
import android.util.Log;

import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.data.db.repository.PiggyBankRepository;
import java.util.GregorianCalendar;

/**
 * Created by Miguel on 04/01/2018.
 */

public class AddPiggyBankInteractor implements AddPiggyBankInteractorInterface {

    @Override
    public void validatePiggyBank(int id, int idUser, String name, GregorianCalendar calendar, OnAddPiggyBankListener listener) {
        if (TextUtils.isEmpty(name)){
            listener.onNameEmptyError();
        } else if (true){ // UserRepository validate credentials User Pass
            Log.e("Editando ",String.valueOf(id));
            if (id < 0){ //si es menor de 0 entonces esque es una Piggybank nueva
                int lastId = PiggyBankRepository.getInstance().getList();
                Log.e("Error",String.valueOf(lastId));
                PiggyBankRepository.getInstance().addPiggyBank(new PiggyBank(lastId++,0,name,calendar));
                listener.onSucces();
            }

        }
    }
}
