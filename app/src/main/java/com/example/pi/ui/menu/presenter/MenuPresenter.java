package com.example.pi.ui.menu.presenter;

import android.view.Menu;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.ui.menu.contract.MenuContract;
import com.example.pi.ui.menu.interactor.MenuInteractor;
import com.example.pi.ui.menu.interactor.MenuInteractorInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 25/02/2018.
 */

public class MenuPresenter implements MenuContract.Presenter, MenuInteractorInterface.OnLoadFinishedListener {

    private MenuContract.View view;
    private MenuInteractorInterface interactor;

    public MenuPresenter(MenuContract.View view){
        this.view = view;
        this.interactor = new MenuInteractor(this);
    }

    @Override
    public void loadPiggyBank() {
        interactor.loadPiggyBank();
    }

    @Override
    public void onSucces(ArrayList<PiggyBank> list) {
        view.showPiggyBank(list);
    }

    @Override
    public void OnDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void options(int i, Object o) {

    }
}
