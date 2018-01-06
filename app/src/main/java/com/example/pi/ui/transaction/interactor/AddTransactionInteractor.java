package com.example.pi.ui.transaction.interactor;

import com.example.pi.data.db.model.Transaction;
import com.example.pi.data.db.repository.TransactionRepository;

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
                int lastId = TransactionRepository.getInstance().getLastId();
                TransactionRepository.getInstance().addTransaction(
                        new Transaction(
                                lastId+1,
                                transaction.getIdUser(),
                                transaction.getIdPiggyBank(),
                                transaction.getIdEstablishment(),
                                transaction.isPayment(),
                                transaction.getAmount(),
                                transaction.getCreationDate(),
                                transaction.getComment(),
                                transaction.getLatitude(),
                                transaction.getLongitude(),
                                transaction.getImage())
                );
            } else {
                TransactionRepository.getInstance().modTransaction(transaction);
            }
            listener.onSuccess();
        }
    }
}
