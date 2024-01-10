/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cusc.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ngomi
 */
@Entity
@Table(name = "FeedbackDetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeedbackDetails.findAll", query = "SELECT f FROM FeedbackDetails f"),
    @NamedQuery(name = "FeedbackDetails.findByFeedbackID", query = "SELECT f FROM FeedbackDetails f WHERE f.feedbackDetailsPK.feedbackID = :feedbackID"),
    @NamedQuery(name = "FeedbackDetails.findByProductID", query = "SELECT f FROM FeedbackDetails f WHERE f.feedbackDetailsPK.productID = :productID"),
    @NamedQuery(name = "FeedbackDetails.findByFeedbackContent", query = "SELECT f FROM FeedbackDetails f WHERE f.feedbackContent = :feedbackContent"),
    @NamedQuery(name = "FeedbackDetails.findByReviews", query = "SELECT f FROM FeedbackDetails f WHERE f.reviews = :reviews")})
public class FeedbackDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FeedbackDetailsPK feedbackDetailsPK;
    @Size(max = 500)
    @Column(name = "FeedbackContent")
    private String feedbackContent;
    @Size(max = 20)
    @Column(name = "Reviews")
    private String reviews;
    @JoinColumn(name = "FeedbackID", referencedColumnName = "FeedbackID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Feedbacks feedbacks;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Products products;

    public FeedbackDetails() {
    }

    public FeedbackDetails(FeedbackDetailsPK feedbackDetailsPK) {
        this.feedbackDetailsPK = feedbackDetailsPK;
    }

    public FeedbackDetails(int feedbackID, int productID) {
        this.feedbackDetailsPK = new FeedbackDetailsPK(feedbackID, productID);
    }

    public FeedbackDetailsPK getFeedbackDetailsPK() {
        return feedbackDetailsPK;
    }

    public void setFeedbackDetailsPK(FeedbackDetailsPK feedbackDetailsPK) {
        this.feedbackDetailsPK = feedbackDetailsPK;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public Feedbacks getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Feedbacks feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackDetailsPK != null ? feedbackDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbackDetails)) {
            return false;
        }
        FeedbackDetails other = (FeedbackDetails) object;
        if ((this.feedbackDetailsPK == null && other.feedbackDetailsPK != null) || (this.feedbackDetailsPK != null && !this.feedbackDetailsPK.equals(other.feedbackDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.cusc.entities.FeedbackDetails[ feedbackDetailsPK=" + feedbackDetailsPK + " ]";
    }
    
}
