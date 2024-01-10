/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.cusc.entities.Owner;
import com.cusc.sessions.OwnerFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;


/**
 *
 * @author ngomi
 */
@Named(value = "ownerMB")
@SessionScoped
public class OwnerMB implements Serializable {

    @EJB
    private OwnerFacadeLocal ownerFacade;
    private Owner owner;
    private String messageConfirmOld;
    private String messageNewPassword;
    private String messageSuccess;

    public OwnerMB() {
    }

    public String changePasswordOwner(String username) {
        owner = ownerFacade.findByUsername(username);
        return "changePasswordOwner";
    }

    public String updateNewPassword() {
        int status = 0;
        if (!owner.getPassword().equals(owner.getConfirmOldPassword())) {
            messageConfirmOld = "Old password invalid!";
            status++;
        }
        if (!owner.getNewPassword().equals(owner.getConfirmNewPassword())) {
            messageNewPassword = "Confirm new password invalid!";
            status++;
        }
        if (status == 0) {
            owner.setPassword(owner.getConfirmNewPassword());
            ownerFacade.edit(owner);
            messageSuccess = "Changed password successfully!";
            messageConfirmOld = "";
            messageNewPassword = "";
        } else {
            messageSuccess = "";
        }
        return "changePasswordOwner";
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getMessageConfirmOld() {
        return messageConfirmOld;
    }

    public void setMessageConfirmOld(String messageConfirmOld) {
        this.messageConfirmOld = messageConfirmOld;
    }

    public String getMessageNewPassword() {
        return messageNewPassword;
    }

    public void setMessageNewPassword(String messageNewPassword) {
        this.messageNewPassword = messageNewPassword;
    }

    public String getMessageSuccess() {
        return messageSuccess;
    }

    public void setMessageSuccess(String messageSuccess) {
        this.messageSuccess = messageSuccess;
    }

}
