package com.example.pi.ui.menu.interactor;

import com.example.pi.data.repository.PiggyBankRepository;

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
}
