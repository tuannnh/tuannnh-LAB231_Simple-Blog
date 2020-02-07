/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Article;
import entities.Comment;
import entities.User;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
public class ArticleDAO implements Serializable {

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

    public int searchByContent(String searchKeyword) throws Exception {
        int result;

        EntityManager em = emf.createEntityManager();
        String sql = "Select a From Article a WHERE  a.status = :status AND (a.title LIKE :search OR a.shortDescription LIKE :search OR a.content LIKE :search) ";
        Query qr = em.createQuery(sql);
        qr.setParameter("search", "%" + searchKeyword + "%");
        qr.setParameter("status", "Active");
        result = qr.getResultList().size();
        return result;
    }

    public List<Article> searchByContentOnPage(String searchKeyword, int pageId) throws Exception {
        List<Article> result;

        int start;
        if (pageId == 1) {
            start = 0;
        } else {
            start = (pageId - 1) * 20;
        }
        EntityManager em = emf.createEntityManager();
        String sql = "Select a From Article a WHERE  a.status = :status AND (a.title LIKE :search OR a.shortDescription LIKE :search OR a.content LIKE :search) ORDER BY a.dateCreate DESC";
        Query qr = em.createQuery(sql);
        qr.setParameter("search", "%" + searchKeyword + "%");
        qr.setParameter("status", "Active");
        qr.setFirstResult(start);
        qr.setMaxResults(20);
        result = qr.getResultList();
        return result;
    }

    public List<Article> searchByContentOnPage(String searchKeyword, String status, int pageId) throws Exception {
        List<Article> result;
        int start;
        if (pageId == 1) {
            start = 0;
        } else {
            start = (pageId - 1) * 20;
        }
        EntityManager em = emf.createEntityManager();
        String sql = "Select a From Article a WHERE  a.status = :status AND (a.title LIKE :search OR a.shortDescription LIKE :search OR a.content LIKE :search) ORDER BY a.dateCreate DESC";
        Query qr = em.createQuery(sql);
        qr.setParameter("search", "%" + searchKeyword + "%");
        qr.setParameter("status", status);
        qr.setFirstResult(start);
        qr.setMaxResults(20);
        result = qr.getResultList();
        return result;
    }

    public int searchByContent(String searchKeyword, String status) throws Exception {
        int result;

        EntityManager em = emf.createEntityManager();
        String sql = "Select a From Article a WHERE  a.status = :status AND (a.title LIKE :search OR a.shortDescription LIKE :search OR a.content LIKE :search)";
        Query qr = em.createQuery(sql);
        qr.setParameter("search", "%" + searchKeyword + "%");
        qr.setParameter("status", status);
        result = qr.getResultList().size();
        return result;
    }

    public Article findById(long articleId) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Article article = em.find(Article.class, articleId);
        em.getTransaction().commit();
        em.close();
        return article;
    }

    private void setCommentList(long articleId) throws Exception {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Article editArticle = em.find(Article.class, articleId);
        Collection<Comment> commentList = editArticle.getCommentCollection();
        em.getTransaction().commit();
        em.close();
    }

    public boolean createArticle(String title, String shortDescription, Date dateCreate, String status, String content, User email) throws Exception {
        EntityManager em = emf.createEntityManager();
        Article newArticle = new Article(title, shortDescription, dateCreate, status, content, email);
        em.getTransaction().begin();
        em.persist(newArticle);
        em.getTransaction().commit();
        return true;
    }

    public boolean deleteArticle(long articleId) throws Exception {
        EntityManager em = emf.createEntityManager();
        Article article = em.find(Article.class, articleId);
        article.setStatus("Deleted");
        em.getTransaction().begin();
        em.merge(article);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean approveArticle(long articleId) throws Exception {
        EntityManager em = emf.createEntityManager();
        Article article = em.find(Article.class, articleId);
        article.setStatus("Active");
        em.getTransaction().begin();
        em.merge(article);
        em.getTransaction().commit();
        return true;

    }

}
