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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tuannnh
 */
public class CommentDAO implements Serializable {

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

    public boolean createComment(String content, Date commentDate, User user, Article article) throws Exception {
        EntityManager em = emf.createEntityManager();
        ArticleDAO dao = new ArticleDAO();
        em.getTransaction().begin();

        Comment newComment = new Comment(content, commentDate, user, article);
        em.persist(newComment);
        article.addComment(newComment);
        em.merge(article);
        em.getTransaction().commit();


        System.out.println(article.getCommentCollection().size());

        return true;
    }

}
