/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cusc.sessions;

import com.cusc.entities.Owner;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ngomi
 */
@Local
public interface OwnerFacadeLocal {

    void create(Owner owner);

    void edit(Owner owner);

    void remove(Owner owner);

    Owner find(Object id);

    List<Owner> findAll();

    List<Owner> findRange(int[] range);

    int count();

    Owner loadByUsername(String username, String password);

    long getCountByUsernamePassword(String username, String password);

    Owner findByUsername(String username);
    
}
