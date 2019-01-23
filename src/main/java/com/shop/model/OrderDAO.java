package com.shop.model;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class OrderDAO {
    
    @Autowired
    SessionFactory sessionFactory;
    
  
    
    public List<Orders> getOrders(){
        Session session = sessionFactory.getCurrentSession();
        List<Orders>orders = session.getNamedQuery("Orders.findAll").list();
        
        return orders;
    }
    
    public Orders getByID(int id){
        Session session = sessionFactory.getCurrentSession();
        Orders order = (Orders) session.get(Orders.class, id);
        
        return order;
    }
}
