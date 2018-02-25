package com.example.pi.ui.menu.interactor;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.data.repository.PiggyBankRepository;
import com.example.pi.data.repository.TransactionRepository;

/**
 * Created by Miguel on 25/02/2018.
 */

public class MenuInteractor implements MenuInteractorInterface {

    private MenuInteractorInterface.OnLoadFinishedListener listener;

    public MenuInteractor(MenuInteractorInterface.OnLoadFinishedListener listener){
        this.listener = listener;
    }
    @Override
    public void loadPiggyBank() {
        listener.onSucces(PiggyBankRepository.getInstance().getPiggybanks());
    }

    @Override
    public void deletePiggyBank(PiggyBank piggyBank) {
        PiggyBankRepository.getInstance().delete(piggyBank);
        listener.onDeletedPiggyBank();
    }

    @Override
    public boolean existsAnyTransactionWithPiggyBankID(int id) {
        return TransactionRepository.getInstance().exists(id);
    }

    @Override
    public void deleteAllTransactionsWithPiggyBankID(int id) {
        TransactionRepository.getInstance().deleteAll(id, listener);
    }
}
