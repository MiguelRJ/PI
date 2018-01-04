package com.example.pi.ui.piggybank.presenter;

import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.ui.piggybank.contract.ListPiggyBankContract;
import com.example.pi.ui.piggybank.interactor.ListPiggyBankInteractor;
import com.example.pi.ui.piggybank.interactor.ListPiggyBankInteractorInterface;

import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      implements ListPiggyBankContract.Presenter
 *      implements ListPiggyBankInteractorInterface.OnLoadFinishedListener
 *      view, interactor
 *      public ListPiggyBankPresenter(ListPiggyBankContract.View view)
 */

public class ListPiggyBankPresenter implements ListPiggyBankContract.Presenter,ListPiggyBankInteractorInterface.OnLoadFinishedListener {

    private ListPiggyBankContract.View view;
    private ListPiggyBankInteractorInterface interactor;

    public ListPiggyBankPresenter(ListPiggyBankContract.View view){
        this.view = view;
        this.interactor = new ListPiggyBankInteractor(this);
    }

    /* implements ListPiggyBankContract.Presenter */
    @Override
    public void OnDestroy() {

    }

    @Override
    public void options(int i, Object o) {

    }

    @Override
    public void loadPiggyBank() {

        interactor.loadPiggyBank();

    }
    /* implements ListPiggyBankContract.Presenter */

    /* ListPiggyBankInteractorInterface.OnLoadFinishedListener */
    @Override
    public void onSucces(List<PiggyBank> list) {
        view.showPiggyBank(list);
    }
    /* ListPiggyBankInteractorInterface.OnLoadFinishedListener */
}
