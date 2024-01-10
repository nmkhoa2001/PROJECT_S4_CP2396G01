/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cusc.sessions;

import com.cusc.entities.Customers;
import com.cusc.entities.Orders;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ngomi
 */
@Local
public interface OrdersFacadeLocal {

    void create(Orders orders);

    void edit(Orders orders);

    void remove(Orders orders);

    Orders find(Object id);

    List<Orders> findAll();

    List<Orders> findRange(int[] range);

    int count();

    public List<Orders> getCustomerOrders(Customers c);
    
    List<Orders> findAllByDate(Date startDate,Date endDate);
    
    List<Orders> findMounth(int Mounth);
    
}
