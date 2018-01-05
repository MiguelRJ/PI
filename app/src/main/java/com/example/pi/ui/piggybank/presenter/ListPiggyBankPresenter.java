package com.example.pi.ui.piggybank.presenter;

import android.text.BoringLayout;

import com.example.pi.data.db.model.PiggyBank;
import com.example.pi.ui.piggybank.contract.ListPiggyBankContract;
import com.example.pi.ui.piggybank.interactor.ListPiggyBankInteractor;
import com.example.pi.ui.piggybank.interactor.ListPiggyBankInteractorInterface;

import java.util.HashMap;
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

    /* Gestion del multichoicelistener */
    HashMap<Integer,Boolean> selection = new HashMap<>();

    @Override
    public void setNewSelection(int position) {
        selection.put(position,true);
    }

    @Override
    public void removeSelection(int position) {
        selection.remove(position);
    }

    @Override
    public void deleteSelection() {
        for (Integer position : selection.keySet()){
            interactor.deletePiggyBank(view.getPiggyBank(position));
        }
    }

    @Override
    public void clearSelection() {
        selection.clear();
    }

    @Override
    public boolean isPositionChecked(int position) {
        return selection.get(position)==null?false:true; // Si es null falso, si no true;
    }
    /* Gestion del multichoicelistener */
}
