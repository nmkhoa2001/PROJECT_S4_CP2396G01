/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cusc.sessions;

import com.cusc.entities.Promotions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ngomi
 */
@Local
public interface PromotionsFacadeLocal {

    void create(Promotions promotions);

    void edit(Promotions promotions);

    void remove(Promotions promotions);

    Promotions find(Object id);

    List<Promotions> findAll();

    List<Promotions> findRange(int[] range);

    int count();
}
