package com.example.pi.ui.piggybank.presenter;

import com.example.pi.ui.piggybank.contract.AddPiggyBankContract;
import com.example.pi.ui.piggybank.interactor.AddPiggyBankInteractor;
import com.example.pi.ui.piggybank.interactor.AddPiggyBankInteractorInterface;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *
 *
 */

public class AddPiggyBankPresenter implements AddPiggyBankContract.Presenter,AddPiggyBankInteractorInterface.OnAddPiggyBankListener {

    private AddPiggyBankContract.View view;
    private AddPiggyBankInteractorInterface interactor;

    public AddPiggyBankPresenter (AddPiggyBankContract.View view){
        this.view = view;
        interactor = new AddPiggyBankInteractor();
    }

    /* implements AddPiggyBankInteractorInterface.OnAddPiggyBankListener */
    @Override
    public void onSucces() {
        view.showOnSucces();
    }

    @Override
    public void onNameEmptyError() {
        view.showNameEmptyError();
    }

    @Override
    public void onDuplicatedName() {
        view.showDuplicatedName();
    }
    /* implements AddPiggyBankInteractorInterface.OnAddPiggyBankListener */

    /* implements AddPiggyBankContract.Presenter */
    @Override
    public void validatePiggyBank(int id, int idUser, String name) {
        interactor.validatePiggyBank(id,idUser,name,this);
    }

    @Override
    public void OnDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void options(int i, Object o) {

    }
    /* implements AddPiggyBankContract.Presenter */
}
