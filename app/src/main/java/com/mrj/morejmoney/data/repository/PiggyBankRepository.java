package com.mrj.morejmoney.data.repository;

import com.mrj.morejmoney.data.base.PiggyBankDaoBase;
import com.mrj.morejmoney.data.db.PIContract;
import com.mrj.morejmoney.data.db.dao.PiggyBankDao;
import com.mrj.morejmoney.data.model.PiggyBank;

import java.util.ArrayList;

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

    public PiggyBank loadPiggyBank(int idPiggyBank){
        return dao.loadPiggybank(idPiggyBank);
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
