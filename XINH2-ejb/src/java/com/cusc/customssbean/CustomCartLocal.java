/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cusc.customssbean;

import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author ngomi
 */
@Local
public interface CustomCartLocal {

    void addCart(Long id, Integer qual);

    public Map<Long, Integer> showCartMap();

    public int countCart();

    public void removeCart(Long id);

    public void emptyCart();  
    
    void plusCart(Long id);
    
    void deCart(Long id);
    
    void clearCart();

    public void updateCart(Long id, boolean flag, Long max);
}
