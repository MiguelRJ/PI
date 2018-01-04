package com.example.pi.ui.piggybank.interactor;

import com.example.pi.data.db.model.PiggyBank;
import java.util.List;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      interface OnLoadFinishedListener
 *          onSucces(List<PiggyBank> list)
 *      loadPiggyBank()
 */

public interface ListPiggyBankInteractorInterface {

    interface OnLoadFinishedListener{

        void onSucces(List<PiggyBank> list);

    }

    void loadPiggyBank(); // Get the list of pojos
}
