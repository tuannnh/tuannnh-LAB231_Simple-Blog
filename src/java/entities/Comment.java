/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tuannnh
 */
@Entity
@Table(name = "tbl_comment", catalog = "BlogDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
    @NamedQuery(name = "Comment.findById", query = "SELECT c FROM Comment c WHERE c.id = :id"),
    @NamedQuery(name = "Comment.findByCommentDate", query = "SELECT c FROM Comment c WHERE c.commentDate = :commentDate")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Lob
    @Column(name = "content", nullable = false, length = 2147483647)
    private String content;
    @Basic(optional = false)
    @Column(name = "commentDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;
    @JoinColumn(name = "articleId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Article articleId;
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    @ManyToOne(optional = false)
    private User email;

    public Comment() {
    }

    public Comment(Long id) {
        this.id = id;
    }

    public Comment(Long id, String content, Date commentDate) {
        this.id = id;
        this.content = content;
        this.commentDate = commentDate;
    }

    public Comment(String content, Date commentDate, User user, Article article) {
        this.content = content;
        this.commentDate = commentDate;
        this.email = user;
        this.articleId = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public String getCommentDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
        String dateString = sdf.format(commentDate);
        return dateString;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Article getArticleId() {
        return articleId;
    }

    public void setArticleId(Article articleId) {
        this.articleId = articleId;
    }

    public User getEmail() {
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Comment[ id=" + id + " ]";
    }

}
