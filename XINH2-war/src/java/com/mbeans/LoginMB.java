/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.cusc.entities.Customers;
import com.cusc.entities.Employees;
import com.cusc.sessions.CustomersFacadeLocal;
import com.cusc.sessions.EmployeesFacadeLocal;
import com.cusc.sessions.OwnerFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author ngomi
 */
@Named(value = "loginMB")
@SessionScoped
public class LoginMB implements Serializable {

    @EJB
    private OwnerFacadeLocal ownerFacade;

    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private EmployeesFacadeLocal employeesFacade;
    
    @Inject
    private LoginMB loginMB;
    
    private String username;
    private String password;
    private int statusLogin;
    private String fullname;
    private String address;
    private String phoneNumber;
    private String email;
    private Customers customer;

    public LoginMB() {
        customer = new Customers();
    }

    @PostConstruct
    public void init() {
        statusLogin = 6;
    }



    public void processLogin() throws IOException {
        long checkEmployee = employeesFacade.getCountByUsernamePassword(username, password);
        long checkOwner = ownerFacade.getCountByUsernamePassword(username, password);
        if (checkEmployee > 0) {
            System.out.println(checkEmployee);
            Employees emp = employeesFacade.loadByUsername(username, password);
            if (emp.getStatus() != 0) {
                statusLogin = 1;
                FacesContext.getCurrentInstance().getExternalContext().redirect("/XINH2-war/faces/admin/productList.xhtml");
            } else {
                statusLogin = 3;
            }
        } else if (checkOwner > 0) {
            statusLogin = 2;
            FacesContext.getCurrentInstance().getExternalContext().redirect("/XINH2-war/faces/admin/employeeList.xhtml");
        } else {
            statusLogin = 3;
            FacesContext.getCurrentInstance().getExternalContext().redirect("/XINH2-war/faces/admin/login.xhtml");
        }

    }

    public void checkLogin() throws IOException {
        if (statusLogin != 2) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/XINH2-war/faces/admin/login.xhtml");
        }
    }

    public void checkLogin1() throws IOException {
        if (statusLogin != 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/XINH2-war/faces/admin/login.xhtml");
        }
    }

    public void checkLoginAdminOwner() throws IOException {
        if (statusLogin != 1 && statusLogin != 2) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/XINH2-war/faces/admin/login.xhtml");
        }
    }

    public void checkCustomer() throws IOException {
        if (statusLogin != 4) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/XINH2-war/faces/client/login.xhtml");
        }
    }

    public LoginMB getLoginMB() {
        return loginMB;
    }

    public void logoutAdmin() throws IOException {
        statusLogin = 3;
        username = "";
        password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/XINH2-war/faces/admin/login.xhtml");
    }

    public void logoutCustomer() throws IOException {
        statusLogin = 5;
        username = "";
        password = "";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/XINH2-war/faces/client/login.xhtml");
    }

    public void setLoginMB(LoginMB loginMB) {
        this.loginMB = loginMB;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(int statusLogin) {
        this.statusLogin = statusLogin;
    }

    public OwnerFacadeLocal getOwnerFacade() {
        return ownerFacade;
    }

    public void setOwnerFacade(OwnerFacadeLocal ownerFacade) {
        this.ownerFacade = ownerFacade;
    }

    public CustomersFacadeLocal getCustomersFacade() {
        return customersFacade;
    }

    public void setCustomersFacade(CustomersFacadeLocal customersFacade) {
        this.customersFacade = customersFacade;
    }

    public EmployeesFacadeLocal getEmployeesFacade() {
        return employeesFacade;
    }

    public void setEmployeesFacade(EmployeesFacadeLocal employeesFacade) {
        this.employeesFacade = employeesFacade;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

}
