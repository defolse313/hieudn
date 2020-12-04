/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.blos;

import hieudn.entitites.Users;
import java.io.Serializable;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
public class AccountBLO implements Serializable{

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookingHotelPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean checkLogin(String username, String password) throws Exception{
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT u FROM Users u WHERE u.userId = :userId AND u.password = :password";
        Query query = em.createQuery(jpql);
        query.setParameter("userId", username);
        query.setParameter("password", password);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
    
    public Users loginPage(String username) throws Exception{
         Users u = null;
         EntityManager em = emf.createEntityManager();
         String jpql = "Users.findByUserId";
         Query query = em.createNamedQuery(jpql);
         query.setParameter("userId", username);
         try {
            u = (Users) query.getSingleResult();
            return u;
        } catch (NoResultException e) {
            return null;
        }
    }
    
        public boolean insertAccount(String userId, String username, String address, String password, Date createDate, String role, String status) {
        EntityManager em = emf.createEntityManager();
        Users u = em.find(Users.class, userId);
        if (u == null) {
            u = new Users(userId, username, address, createDate, password, role, "active");
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
