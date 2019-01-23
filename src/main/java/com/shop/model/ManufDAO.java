package com.shop.model;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class ManufDAO {
    
    @Autowired
    SessionFactory sessionFactory;
    
    
    public Manufacturer getManufById(int id){
        Session session = sessionFactory.getCurrentSession();
        Manufacturer man = (Manufacturer)session.get(Manufacturer.class, id);
        return man;
        
    }
    
    public List<Manufacturer> getManuf(){
        Session session = sessionFactory.getCurrentSession();
        List<Manufacturer> result = session.getNamedQuery("Manufacturer.findAll").list();
        return result;
    }
}
