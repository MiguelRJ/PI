package com.mrj.morejmoney.ui.transaction.interactor;

import com.mrj.morejmoney.data.model.Transaction;
import com.mrj.morejmoney.data.repository.PiggyBankRepository;
import com.mrj.morejmoney.data.repository.TransactionRepository;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 06/01/18
 *
 */

public class AddTransactionInteractor implements AddTransactionInteractorInterface{

    @Override
    public void validateTransaction(Transaction transaction , AddTransactionInteractorInterface.OnAddTransactionListener listener) {
        if (transaction.getAmount().isNaN()){
            listener.onAmountEmptyError();
        } else if (true){ // UserRepository validate credentials User Pass
            if (transaction.getId() < 0) { //si es menor de 0 entonces esque es una Piggybank nueva
                TransactionRepository.getInstance().add(transaction);
            } else {
                TransactionRepository.getInstance().udpate(transaction);
            }
            listener.onSuccess();
        }
    }

    @Override
    public void loadPiggyBank(AddTransactionInteractorInterface.OnAddTransactionListener listener) {
        listener.showPiggyBankOnSpinner(PiggyBankRepository.getInstance().getPiggybanks());
    }
}
