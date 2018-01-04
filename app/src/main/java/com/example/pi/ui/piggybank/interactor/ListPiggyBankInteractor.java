package com.example.pi.ui.piggybank.interactor;

import com.example.pi.data.db.repository.PiggyBankRepository;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      private ListPiggyBankInteractorInterface.OnLoadFinishedListener listener;
 *      public ListPiggyBankInteractor()
 *      loadPiggyBank()
 */

public class ListPiggyBankInteractor implements ListPiggyBankInteractorInterface {

    private ListPiggyBankInteractorInterface.OnLoadFinishedListener listener;

    public ListPiggyBankInteractor(ListPiggyBankInteractorInterface.OnLoadFinishedListener listener){
        this.listener = listener;
    }

    /**
     * Get the list of pojos with the instance of repository
     */
    @Override
    public void loadPiggyBank() {

        listener.onSucces(PiggyBankRepository.getInstance().getPiggybanks());

    }
}
