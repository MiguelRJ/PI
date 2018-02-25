package com.example.pi.ui.menu.interactor;

import com.example.pi.data.model.PiggyBank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 25/02/2018.
 */

public interface MenuInteractorInterface {

    interface OnLoadFinishedListener{
        void onSucces(ArrayList<PiggyBank> list);
    }

    void loadPiggyBank();
}
