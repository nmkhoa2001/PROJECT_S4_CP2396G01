/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbeans;

import com.cusc.entities.Employees;
import com.cusc.sessions.EmployeesFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author ngomi
 */
@Named(value = "employeeAccountMB")
@SessionScoped
public class EmployeeAccountMB implements Serializable {

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    private boolean enabled = true;
    private String messageEnableStatusAccount = "";
    private String messageDisableStatusAccount = "";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String toggleEnabled(long id) {
        Employees emp = employeesFacade.find(id);
        if (emp.getStatus() == 0) {
            emp.setStatus(1);
            messageEnableStatusAccount = "Account enabled successfully!";
        } else {
            emp.setStatus(0);
            messageDisableStatusAccount = "Account disabled successfully!";
        }
        employeesFacade.edit(emp);
        return "employeeList";
    }

    public EmployeeAccountMB() {
    }

    public EmployeesFacadeLocal getEmployeesFacade() {
        return employeesFacade;
    }

    public void setEmployeesFacade(EmployeesFacadeLocal employeesFacade) {
        this.employeesFacade = employeesFacade;
    }

    public String getMessageEnableStatusAccount() {
        return messageEnableStatusAccount;
    }

    public void setMessageEnableStatusAccount(String messageEnableStatusAccount) {
        this.messageEnableStatusAccount = messageEnableStatusAccount;
    }

    public String getMessageDisableStatusAccount() {
        return messageDisableStatusAccount;
    }

    public void setMessageDisableStatusAccount(String messageDisableStatusAccount) {
        this.messageDisableStatusAccount = messageDisableStatusAccount;
    }

   

}
