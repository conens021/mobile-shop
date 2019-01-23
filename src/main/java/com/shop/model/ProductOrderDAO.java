package com.shop.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ProductOrderDAO {

    @Autowired
    SessionFactory sessionFactory;
    
    
    public  List<ProductOrders> getByOrderId(Orders order){
        Session session = sessionFactory.getCurrentSession();
        List<ProductOrders> pol = session.getNamedQuery("ProductOrders.findByOrderId").setParameter("orderId", order).list();
        
        return pol;
    }
    
    public void saveProductOrder(List<Product> products, BigDecimal totalPrice, Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Orders order = new Orders();
        order.setOrderTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        order.setCustomerId(customer);

        for (Product p : products) {
            ProductOrders po = new ProductOrders();
            po.setProductId(p);
            po.setOrderId(order);
            po.setProdQuant(p.getQuant());
            
            session.save(po);
        }

    }

}
