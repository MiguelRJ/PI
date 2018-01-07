package com.example.pi.data.db.repository;

import android.util.Log;
import android.widget.Toast;

import com.example.pi.data.db.model.PiggyBank;
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

    private ArrayList<PiggyBank> piggybanks;
    private static PiggyBankRepository piggyBankRepository;

    static {
        piggyBankRepository = new PiggyBankRepository();
    }

    private PiggyBankRepository(){
        this.piggybanks = new ArrayList<>();
        //initialize();
    }

    public void resetArray(){
        piggybanks = new ArrayList<>();
    }

    public void initialize(){
        addPiggyBank(new PiggyBank(0,0,"Cartera0"));
        addPiggyBank(new PiggyBank(1,0,"Almohada0",new GregorianCalendar(2017,11-1,17,15,30,00)));
        addPiggyBank(new PiggyBank(2,0,"Piedra0"));
        addPiggyBank(new PiggyBank(3,0,"FundaMovil0",new GregorianCalendar(2017,11-1,17,15,30,00)));
        addPiggyBank(new PiggyBank(4,0,"Casa0"));
        addPiggyBank(new PiggyBank(5,0,"Viaje0"));
        addPiggyBank(new PiggyBank(6,0,"1"));
        addPiggyBank(new PiggyBank(7,0,"2"));
        addPiggyBank(new PiggyBank(8,0,"3"));
        addPiggyBank(new PiggyBank(9,0,"4"));
        addPiggyBank(new PiggyBank(4,1,"Cartera1"));
        addPiggyBank(new PiggyBank(5,1,"Almohada1",new GregorianCalendar(2017,11-1,17,15,30,00)));
        addPiggyBank(new PiggyBank(6,1,"Piedra1"));
        addPiggyBank(new PiggyBank(7,1,"FundaMovil1",new GregorianCalendar(2017,11-1,17,15,30,00)));
    }

    /* GET INSTANCE OF REPOSITORY */
    public static PiggyBankRepository getInstance(){
        return piggyBankRepository;
    }

    /* ADD PIGGY BANK */
    public void addPiggyBank(PiggyBank piggyBank){
        if (AppPreferencesHelper.getInstance().getCurrentUserId() == piggyBank.getIdUser()) {
            piggybanks.add(piggyBank);
        }
    }

    public PiggyBank getPiggyBank(int id){
        return getPiggybanks().get(id);
    }

    public int getLastId(){
        int id = 0;
        Log.e("lastid",String.valueOf(id));
        for (int i=0; i < piggybanks.size(); i++) {
            Log.e("lastid",String.valueOf(i));
            if (piggybanks.get(i).getId() > id){
                id = piggybanks.get(i).getId();
            }
        }
        return id;
    }

    public String getList(){
        String mensaje = "";
        for (int i=0; i < piggybanks.size(); i++) {
            mensaje += String.valueOf(piggybanks.get(i).getId()+" ");
        }
        return mensaje;
    }

    /**
     * modifica una piggybank por su id e idUser
     * modificando el nombre y la fecha
     * @param id
     * @param idUser
     * @param name
     * @param calendar
     */
    public void modPiggyBank(int id, int idUser, String name, GregorianCalendar calendar){
        Iterator<PiggyBank> iterator = piggybanks.iterator();
        PiggyBank piggyBank;
        while (iterator.hasNext()){
            piggyBank = iterator.next();
            if ( (piggyBank.getId() == id) && piggyBank.getIdUser() == idUser ){
                piggyBank.setName(name);
                piggyBank.setCreationDate(calendar);
                break;
            }
        }
    }

    /**
     * Eliminar una piggybank con un iterator
     * @param p
     */
    public void deletePiggyBank(PiggyBank p){
        Iterator<PiggyBank> iterator = piggybanks.iterator();
        PiggyBank piggyBank;
        while (iterator.hasNext()){
            piggyBank = iterator.next();
            if ( (piggyBank.getId() == p.getId()) && piggyBank.getIdUser() == p.getIdUser() ){
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Metodo para comprobar si ya existe una piggybank con los mismos datos
     * @param name
     * @return
     */
    public boolean existsPiggyBankBy(String name) {
        Iterator<PiggyBank> iterator = piggybanks.iterator();
        PiggyBank piggyBank;
        while (iterator.hasNext()){
            piggyBank = iterator.next();
            if (piggyBank.getName().toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para comprobar si ya existe una piggybank con los mismos datos
     * @param name
     * @return
     */
    public boolean existsPiggyBankBy(String name, Calendar calendar) {
        Iterator<PiggyBank> iterator = piggybanks.iterator();
        PiggyBank piggyBank;
        while (iterator.hasNext()){
            piggyBank = iterator.next();
            if (piggyBank.getName().toLowerCase().equals(name.toLowerCase()) && piggyBank.getCreationDate().equals(calendar)) {
                return true;
            }
        }
        return false;
    }

    /* GET PIGGY BANKS ORDER BY */
    public ArrayList<PiggyBank> getPiggybanks(){
        /*ArrayList<PiggyBank> piggyBanksById = new ArrayList<>();
        Iterator<PiggyBank> iterator = piggybanks.iterator();
        PiggyBank piggyBank;
        while (iterator.hasNext()){
            piggyBank = iterator.next();
            if (piggyBank.getIdUser() == AppPreferencesHelper.getInstance().getCurrentUserId()){
                piggyBanksById.add(piggyBank);
            }
        }
        Collections.sort(piggyBanksById);
        return piggyBanksById;*/
        Collections.sort(piggybanks);
        return piggybanks;
    }

    public ArrayList<PiggyBank> getPiggybanksOrderByCreationDate(){
        Collections.sort(piggybanks, new PiggyBank.PiggyBankOrderByCreationDate());
        return piggybanks;
    }

    public ArrayList<PiggyBank> getPiggybanksOrderByTotalAmount(){
        Collections.sort(piggybanks, new PiggyBank.PiggyBankOrderByTotalAmount());
        return piggybanks;
    }

    public ArrayList<PiggyBank> getPiggybanksOrderById(){
        Collections.sort(piggybanks, new PiggyBank.PiggyBankOrderById());
        return piggybanks;
    }

}
