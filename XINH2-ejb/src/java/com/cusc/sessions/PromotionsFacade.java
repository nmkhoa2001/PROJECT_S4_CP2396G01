/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cusc.sessions;

import com.cusc.entities.Promotions;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ngomi
 */
@Stateless
public class PromotionsFacade extends AbstractFacade<Promotions> implements PromotionsFacadeLocal {

    @PersistenceContext(unitName = "XINH2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PromotionsFacade() {
        super(Promotions.class);
    }
    
}
