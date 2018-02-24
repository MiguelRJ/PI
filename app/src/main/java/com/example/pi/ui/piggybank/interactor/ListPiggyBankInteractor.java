package com.example.pi.ui.piggybank.interactor;

import android.os.AsyncTask;

import com.example.pi.data.model.PiggyBank;
import com.example.pi.data.repository.PiggyBankRepository;

import java.util.ArrayList;

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
        new AsyncTask<Void, Void, ArrayList<PiggyBank>>() {

            @Override
            protected void onPreExecute() {
                listener.showProgress();
            }

            @Override
            protected ArrayList<PiggyBank> doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return PiggyBankRepository.getInstance().getPiggybanks();
            }

            @Override
            protected void onPostExecute(ArrayList<PiggyBank> piggyBanks) {
                listener.dismissProgress();
                listener.onSucces(piggyBanks);
            }
        }.execute();
    }

    @Override
    public void deletePiggyBank(PiggyBank piggyBank) {
        PiggyBankRepository.getInstance().delete(piggyBank);
        listener.onDeletedPiggyBank();
    }
}
