/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.cusc.entities.Products;
import com.cusc.entities.Suppliers;
import com.cusc.sessions.SuppliersFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author ngomi
 */
@Named(value = "supplierMB")
@SessionScoped
public class SupplierMB implements Serializable {

    /**
     * Creates a new instance of SupplierMB
     */
     @EJB
    private SuppliersFacadeLocal suppliersFacade;
    private Integer supID;
    private String companyName;
    private String phone;
    private String address;
    private String email;
    private String webpage;
    private int status;
    private Suppliers supplier;

    /**
     * Creates a new instance of supplierMB
     */
    public SupplierMB() {
        supplier = new Suppliers();
    }

    public List<Suppliers> showAllSupplier() {
        return suppliersFacade.findAll();
    }

    public String showAddForm() {
        supplier = new Suppliers();
        return "addSupplier";
    }

    public String createSupplier() {
        suppliersFacade.create(supplier);
        return "supplierList";
    }

    public String showUpdate(Integer id) {
        supplier = suppliersFacade.find(id);
        return "editSupplier";
    }

    public String saveUpdate() {
        suppliersFacade.edit(supplier);
        return "supplierList";
    }

    public String deleteSupplier(Integer id) {
        suppliersFacade.remove(suppliersFacade.find(id));
//        supplier = suppliersFacade.find(id);
//        suppliersFacade.remove(supplier);
        return "supplierList";
    }

    public boolean validDelete(Integer id) {
        Suppliers s = suppliersFacade.find(id);
        List<Products> listProduct = new ArrayList<>();
        listProduct = (List<Products>)s.getProductsCollection();
        if (listProduct.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public SuppliersFacadeLocal getSuppliersFacade() {
        return suppliersFacade;
    }

    public void setSuppliersFacade(SuppliersFacadeLocal suppliersFacade) {
        this.suppliersFacade = suppliersFacade;
    }

    public Integer getSupID() {
        return supID;
    }

    public void setSupID(Integer supID) {
        this.supID = supID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    
}
