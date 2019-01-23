/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nrack
 */
@Entity
@Table(name = "manufacturers")
@NamedQueries({
    @NamedQuery(name = "Manufacturer.findAll", query = "SELECT m FROM Manufacturer m")
    , @NamedQuery(name = "Manufacturer.findByManufId", query = "SELECT m FROM Manufacturer m WHERE m.manufId = :manufId")
    , @NamedQuery(name = "Manufacturer.findByManufName", query = "SELECT m FROM Manufacturer m WHERE m.manufName = :manufName")})
public class Manufacturer implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufId")
    private Collection<Product> productCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "manuf_id")
    private Integer manufId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "manuf_name")
    private String manufName;

    public Manufacturer() {
    }

    public Manufacturer(Integer manufId) {
        this.manufId = manufId;
    }

    public Manufacturer(Integer manufId, String manufName) {
        this.manufId = manufId;
        this.manufName = manufName;
    }

    public Integer getManufId() {
        return manufId;
    }

    public void setManufId(Integer manufId) {
        this.manufId = manufId;
    }

    public String getManufName() {
        return manufName;
    }

    public void setManufName(String manufName) {
        this.manufName = manufName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (manufId != null ? manufId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manufacturer)) {
            return false;
        }
        Manufacturer other = (Manufacturer) object;
        if ((this.manufId == null && other.manufId != null) || (this.manufId != null && !this.manufId.equals(other.manufId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shop.model.Manufacturer[ manufId=" + manufId + " ]";
    }

    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }
    
}
