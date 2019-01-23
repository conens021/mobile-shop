/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author nrack
 */
@Entity
@Table(name = "product_orders")
@NamedQueries({
    @NamedQuery(name = "ProductOrders.findAll", query = "SELECT p FROM ProductOrders p")
    , @NamedQuery(name = "ProductOrders.findByProductOrdersId", query = "SELECT p FROM ProductOrders p WHERE p.productOrdersId = :productOrdersId")
    , @NamedQuery(name = "ProductOrders.findByProdQuant", query = "SELECT p FROM ProductOrders p WHERE p.prodQuant = :prodQuant")
    , @NamedQuery(name="ProductOrders.findByOrderId",query = "SELECT p FROM ProductOrders p WHERE p.orderId = :orderId")})
public class ProductOrders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_orders_id")
    private Integer productOrdersId;
    @Column(name = "prod_quant")
    private Integer prodQuant;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    private Orders orderId;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false,cascade = CascadeType.ALL)
    private Product productId;

    public ProductOrders() {
    }

    public ProductOrders(Integer productOrdersId) {
        this.productOrdersId = productOrdersId;
    }

    public Integer getProductOrdersId() {
        return productOrdersId;
    }

    public void setProductOrdersId(Integer productOrdersId) {
        this.productOrdersId = productOrdersId;
    }

    public Integer getProdQuant() {
        return prodQuant;
    }

    public void setProdQuant(Integer prodQuant) {
        this.prodQuant = prodQuant;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productOrdersId != null ? productOrdersId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductOrders)) {
            return false;
        }
        ProductOrders other = (ProductOrders) object;
        if ((this.productOrdersId == null && other.productOrdersId != null) || (this.productOrdersId != null && !this.productOrdersId.equals(other.productOrdersId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shop.model.ProductOrders[ productOrdersId=" + productOrdersId + " ]";
    }
    
}
