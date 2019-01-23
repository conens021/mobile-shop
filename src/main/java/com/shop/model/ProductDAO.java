package com.shop.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    public Collection<Product> getByManuf(int id) {
        Session session = sessionFactory.getCurrentSession();
        Collection<Product> result = session.getNamedQuery("Product.findByManuf").setInteger("manId", id).list();

        return result;
    }

    public List<Product> getFresh(int maxresult) {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = session.getNamedQuery("Product.findFresh").setMaxResults(8 + maxresult).list();
        
        return result;
    }

    public List<Product> getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        List<Product> result = session.getNamedQuery("Product.findByProductName").setString("prodName", "%" + name.toLowerCase() + "%").list();

        return result;
    }

    public void saveProduct(String name, String desc, String pict, BigDecimal price, BigDecimal superDisc, Manufacturer manufId) {
        Session session = sessionFactory.getCurrentSession();
        Product product = new Product();
        product.setProductName(name);
        product.setProductDescription(desc);
        product.setProductPicture(pict);
        product.setProductPrice(price);
        product.setSuperDiscount(superDisc);
        product.setManufId(manufId);
        product.setUploadTime(new Date());

        session.save(product);
    }

    public Product getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product p = (Product) session.get(Product.class, id);

        return p;
    }
}
