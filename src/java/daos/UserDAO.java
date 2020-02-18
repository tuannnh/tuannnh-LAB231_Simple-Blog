/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.User;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author tuannnh
 */
public class UserDAO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LAB231_BlogPU");

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

    public User checkLogin(String email, String password) throws Exception {
        User user = null;

        EntityManager em = emf.createEntityManager();
        String sql = "Select u From User u Where u.email = :email And u.password =:password And u.status =:status";
        Query qr = em.createQuery(sql);
        qr.setParameter("email", email);
        qr.setParameter("password", password);
        qr.setParameter("status", "Active");

        if (qr.getResultList().size() > 0) {
            user = ((User) qr.getSingleResult());
        }
        return user;
    }

    public String checkStatus(String email) throws Exception {
        String status = "failed";
        EntityManager em = emf.createEntityManager();
        String sql = "Select u.status From User u Where u.email =:email";
        Query qr = em.createQuery(sql);
        qr.setParameter("email", email);
        if (qr.getResultList().size() > 0) {
            status = (String) qr.getSingleResult();
        }
        return status;

    }

    public boolean activeUser(String token) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<User> result = em.createNamedQuery("User.findByToken", User.class).setParameter("token", token).getResultList();
        if (result.size() > 0) {
            User activateUser = em.find(User.class, result.get(0).getEmail());
            em.getTransaction().begin();
            activateUser.setStatus("Active");
            em.merge(activateUser);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    public boolean register(String email, String name, String password, String token) throws Exception {
        EntityManager em = emf.createEntityManager();
        User newUser = em.find(User.class, email);
        if (newUser == null) {
            newUser = new User(email, name, password, "User", "New", token);
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
