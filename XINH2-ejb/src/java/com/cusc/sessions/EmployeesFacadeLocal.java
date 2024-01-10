/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cusc.sessions;

import com.cusc.entities.Employees;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ngomi
 */
@Local
public interface EmployeesFacadeLocal {

    void create(Employees employees);

    void edit(Employees employees);

    void remove(Employees employees);

    Employees find(Object id);

    List<Employees> findAll();

    List<Employees> findRange(int[] range);

    int count();

    public boolean checkLoginEmployee(String username, String password);

    Employees findByUsername(String username);

    Long loginEmployee(String username, String password);

    Employees loadByUsername(String username, String password);

    long getCountByUsernamePassword(String username, String password);

    public Employees checkExistLogin(String username);
    
    long getCountByUsername(String username);
    
}
