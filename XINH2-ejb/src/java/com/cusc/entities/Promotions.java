/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cusc.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ngomi
 */
@Entity
@Table(name = "Promotions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotions.findAll", query = "SELECT p FROM Promotions p")
    , @NamedQuery(name = "Promotions.findByPromotionID", query = "SELECT p FROM Promotions p WHERE p.promotionID = :promotionID")
    , @NamedQuery(name = "Promotions.findByPromotionName", query = "SELECT p FROM Promotions p WHERE p.promotionName = :promotionName")
    , @NamedQuery(name = "Promotions.findByPromotionDate", query = "SELECT p FROM Promotions p WHERE p.promotionDate = :promotionDate")
    , @NamedQuery(name = "Promotions.findByDiscount", query = "SELECT p FROM Promotions p WHERE p.discount = :discount")
    , @NamedQuery(name = "Promotions.findByAmountApplyPromotion", query = "SELECT p FROM Promotions p WHERE p.amountApplyPromotion = :amountApplyPromotion")
    , @NamedQuery(name = "Promotions.findByNote", query = "SELECT p FROM Promotions p WHERE p.note = :note")})
public class Promotions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PromotionID")
    private Long promotionID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "PromotionName")
    private String promotionName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PromotionDate")
    @Temporal(TemporalType.DATE)
    private Date promotionDate;

    @Column(name = "DateEnd")
    private Date dateEnd;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Discount")
    private int discount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "AmountApplyPromotion")
    private double amountApplyPromotion;
    @Size(max = 255)
    @Column(name = "Note")
    private String note;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne(optional = false)
    private Products productID;

    public Promotions() {
    }

    public Promotions(Long promotionID) {
        this.promotionID = promotionID;
    }

    public Promotions(Long promotionID, String promotionName, Date promotionDate, int discount, double amountApplyPromotion) {

        this.promotionID = promotionID;
        this.promotionName = promotionName;
        this.promotionDate = promotionDate;
        this.discount = discount;
        this.amountApplyPromotion = amountApplyPromotion;
    }

    public Long getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(Long promotionID) {
        this.promotionID = promotionID;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Date getPromotionDate() {
        return promotionDate;
    }

    public void setPromotionDate(Date promotionDate) {
        this.promotionDate = promotionDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getAmountApplyPromotion() {
        return amountApplyPromotion;
    }

    public void setAmountApplyPromotion(double amountApplyPromotion) {
        this.amountApplyPromotion = amountApplyPromotion;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionID != null ? promotionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotions)) {
            return false;
        }
        Promotions other = (Promotions) object;
        if ((this.promotionID == null && other.promotionID != null) || (this.promotionID != null && !this.promotionID.equals(other.promotionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cusc.entities.Promotions[ promotionID=" + promotionID + " ]";
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

}
