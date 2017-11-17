package com.example.pi.data.db.repository;

import com.example.pi.data.db.model.Establishment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 17/11/17
 *      ArrayList<Establishment>
 *      establishmentRepository
 *      Constructor
 *      initialize
 *      getInstance() of Repository
 *      addEstablishment()
 *      getEstablishments()
 *      getEstablishmentsOrderByNif()
 */

public class EstablishmentRepository {

    private ArrayList<Establishment> establishments;
    private static EstablishmentRepository establishmentRepository;

    static {
        establishmentRepository = new EstablishmentRepository();
    }

    private EstablishmentRepository(){
        establishments = new ArrayList<>();
        initialize();
    }

    public void initialize(){
        addEstablishment(new Establishment(0,0,"Tienda1","1234567890A","c/ portada nº9 1ºA",new GregorianCalendar(2017,11,17,16,30,00)));
        addEstablishment(new Establishment(0,0,"Tienda2","1234567890B","c/ portada nº9 1ºB"));
    }

    /* GET INSTANCE OF REPOSITORY */
    public EstablishmentRepository getInstance(){
        return establishmentRepository;
    }

    /* ADD ESTABLISHMENT */
    public void addEstablishment(Establishment establishment){
        establishments.add(establishment);
    }

    /* GET ESTABLISHMENT ORDER BY */
    public ArrayList<Establishment> getEstablishments(){
        Collections.sort(establishments);
        return establishments;
    }

    public ArrayList<Establishment> getEstablishmentsOrderByNif(){
        Collections.sort(establishments,new Establishment.EstablishmentOrderByNif());
        return establishments;
    }
}
