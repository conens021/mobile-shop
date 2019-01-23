package com.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import javax.validation.constraints.Size;

/**
 *
 * @author nrack
 */
@Entity
@Table(name = "product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
    ,@NamedQuery(name = "Product.findFresh", query = "SELECT p FROM Product p order by p.uploadTime DESC")
    , @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId")
    , @NamedQuery(name = "Product.findByProductName", query = "FROM Product p WHERE lower(p.productName) LIKE :prodName")
    , @NamedQuery(name = "Product.findByProductPrice", query = "SELECT p FROM Product p WHERE p.productPrice = :productPrice")
    , @NamedQuery(name = "Product.findBySuperDiscount", query = "SELECT p FROM Product p WHERE p.superDiscount = :superDiscount")
    , @NamedQuery(name = "Product.findByProductPicture", query = "SELECT p FROM Product p WHERE p.productPicture = :productPicture"),
      @NamedQuery(name="Product.findByManuf",query = "SELECT p FROM Product p where p.manufId = :manId")
    ,@NamedQuery(name="Product.countByManuf",query = "SELECT count(p) from Product p where p.manufId = :manufID")})
public class Product implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<ProductOrders> productOrdersList;

    @Column(name = "upload_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Integer productId;
    @Basic(optional = false)
    @Size(min = 1, max = 320)
    @Column(name = "product_name")
    private String productName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "product_price")
    private BigDecimal productPrice;
    @Lob
    @Size(max = 16777215)
    @Column(name = "product_description")
    private String productDescription;
    
    @Column(name = "super_discount")
    private BigDecimal superDiscount;
    @Basic(optional = false)
    @Size(min = 1, max = 1024)
    @Column(name = "product_picture")
    private String productPicture;
    @JoinColumn(name = "manuf_id", referencedColumnName = "manuf_id")
    @ManyToOne(optional = false)
    private Manufacturer manufId;
   
    
    @Transient
    public int quant = 0;
    
    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Product(Integer productId, String productName, String productPicture) {
        this.productId = productId;
        this.productName = productName;
        this.productPicture = productPicture;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }
    
    
    
    public Integer getProductId() {
        return productId;
    }
    
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

  

    public BigDecimal getSuperDiscount() {
        return superDiscount;
    }

    public void setSuperDiscount(BigDecimal superDiscount) {
        this.superDiscount = superDiscount;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public Manufacturer getManufId() {
        return manufId;
    }

    public void setManufId(Manufacturer manufId) {
        this.manufId = manufId;
    }

     public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<ProductOrders> getProductOrdersList() {
        return productOrdersList;
    }

    public void setProductOrdersList(List<ProductOrders> productOrdersList) {
        this.productOrdersList = productOrdersList;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }
    
    public BigDecimal totalProductPrice(){
        if(quant >=10)
          return   this.productPrice.multiply(new BigDecimal(quant)).subtract(superDiscount);
        return   this.productPrice.multiply(new BigDecimal(quant));
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shop.model.Product[ productId=" + productId + " ]";
    }

   
    
}
