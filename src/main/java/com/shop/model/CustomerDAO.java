package com.shop.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class CustomerDAO {

    @Autowired
    SessionFactory sessionFactory;
    
    public Customer getById(int id){
        Session session = sessionFactory.getCurrentSession();
        Customer cust = (Customer)session.get(Customer.class,id);
        return cust;
    }
    
    public void saveCustomer(Customer customer){
        Session session = sessionFactory.getCurrentSession();
        session.save(customer);
    }
}
