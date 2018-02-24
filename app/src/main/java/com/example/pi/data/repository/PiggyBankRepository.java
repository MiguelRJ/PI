package com.example.pi.data.repository;

import android.util.Log;

import com.example.pi.data.base.PiggyBankDaoBase;
import com.example.pi.data.db.PIContract;
import com.example.pi.data.db.dao.PiggyBankDao;
import com.example.pi.data.model.PiggyBank;
import com.example.pi.data.prefs.AppPreferencesHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *      ArrayList<PiggyBank>
 *      piggyBankRepository
 *      Constructor
 *      initialize
 *      getInstance() of Repository
 *      addPiggyBank()
 *      getPiggybanks()
 *      getPiggybanksOrderByCreationDate()
 *      getPiggybanksOrderByTotalAmount()
 */

public class PiggyBankRepository {

    //private ArrayList<PiggyBank> piggybanks;
    private static PiggyBankRepository piggyBankRepository;
    private PiggyBankDaoBase dao;

    static {
        piggyBankRepository = new PiggyBankRepository();
    }

    private PiggyBankRepository(){
        //this.piggybanks = new ArrayList<>();
        this.dao = new PiggyBankDao();
    }

    /* GET INSTANCE OF REPOSITORY */
    public static PiggyBankRepository getInstance(){
        return piggyBankRepository;
    }

    public void add(PiggyBank piggyBank){
        long id = dao.add(piggyBank);
        if (id == -1){
            // error
        } else {
            // success
        }
    }

    public void udpate(PiggyBank piggyBank){
        int id = dao.update(piggyBank);
        if (id == -1){
            // error
        } else {
            // success
        }
    }

    public void delete(PiggyBank piggyBank){
        int id = dao.delete(piggyBank);
        if (id == 0){
            // error
        } else {
            // success
        }
    }

    /* GET PIGGY BANKS ORDER BY */
    public ArrayList<PiggyBank> getPiggybanks(){
        return dao.loadAll(PIContract.PiggyBankEntry.DEFAULT_SORT);
    }

    public ArrayList<PiggyBank> getPiggybanksOrderByCreationDate(){
        return dao.loadAll(PIContract.PiggyBankEntry.COL_DATE);
    }

    public ArrayList<PiggyBank> getPiggybanksOrderByTotalAmount(){
        return dao.loadAll(PIContract.PiggyBankEntry.COL_AMOUNT);
    }

    public ArrayList<PiggyBank> getPiggybanksOrderById(){
        return dao.loadAll(PIContract.PiggyBankEntry.DEFAULT_SORT);
    }

}
