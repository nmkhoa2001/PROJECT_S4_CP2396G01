/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cusc.sessions;

import com.cusc.entities.Employees;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 *
 * @author ngomi
 */
@Stateless
public class EmployeesFacade extends AbstractFacade<Employees> implements EmployeesFacadeLocal {

    @PersistenceContext(unitName = "XINH2-ejbPU")
   private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeesFacade() {
        super(Employees.class);
    }

    @Override
    public boolean checkLoginEmployee(String username, String password) {
        boolean flag = false;
        try {
            Query query = em.createQuery("select u from Employees u where u.username =:uname and u.password = :pword");
            query.setParameter("uname", username);
            query.setParameter("pword", password);
            query.getSingleResult();
            flag = true;
        } catch (NoResultException ex) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Employees findByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root r = cq.from(Employees.class);
        cq.select(r);
        cq.where(cb.equal(r.get("username"), username));
        Query query = em.createQuery(cq);
        return (Employees) query.getSingleResult();
    }

    @Override
    public Long loginEmployee(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root r = cq.from(Employees.class);
        cq.select(cb.count(r.get("username")));
        cq.where(cb.and(cb.equal(r.get("username"), username), cb.equal(r.get("password"), password)));
        Query query = em.createQuery(cq);
        return (Long) query.getSingleResult();
    }

    @Override
    public Employees loadByUsername(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Employees.class);
        cq.select(root);
        cq.where(cb.and(cb.equal(root.get("username"), username), cb.equal(root.get("password"), password)));
        Query query = em.createQuery(cq);
        return (Employees) query.getSingleResult();
    }

    @Override
    public long getCountByUsernamePassword(String username, String password) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Employees.class);
        cq.select(cb.count(root.get("username")));
        cq.where(cb.and(cb.equal(root.get("username"), username), cb.equal(root.get("password"), password)));
        Query query = em.createQuery(cq);
        return (long) query.getSingleResult();
    }

    @Override
    public Employees checkExistLogin(String username) {
        Query query = em.createQuery("SELECT e FROM Employees e WHERE e.username = :username");
        query.setParameter("username", username);

        try {
            return (Employees) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public long getCountByUsername(String username) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(Employees.class);
        cq.select(cb.count(root.get("username")));
        cq.where(cb.equal(root.get("username"), username));
        Query query = em.createQuery(cq);
        return (long) query.getSingleResult();
    }

}
