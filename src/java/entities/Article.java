/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tuannnh
 */
@Entity
@Table(name = "tbl_article", catalog = "BlogDB", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findById", query = "SELECT a FROM Article a WHERE a.id = :id"),
    @NamedQuery(name = "Article.findByTitle", query = "SELECT a FROM Article a WHERE a.title = :title"),
    @NamedQuery(name = "Article.findByShortDescription", query = "SELECT a FROM Article a WHERE a.shortDescription = :shortDescription"),
    @NamedQuery(name = "Article.findByDateCreate", query = "SELECT a FROM Article a WHERE a.dateCreate = :dateCreate"),
    @NamedQuery(name = "Article.findByStatus", query = "SELECT a FROM Article a WHERE a.status = :status")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Basic(optional = false)
    @Column(name = "shortDescription", nullable = false, length = 255)
    private String shortDescription;
    @Basic(optional = false)
    @Column(name = "dateCreate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 10)
    private String status;
    @Basic(optional = false)
    @Lob
    @Column(name = "content", nullable = false, length = 2147483647)
    private String content;
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    @ManyToOne(optional = false)
    private User email;
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "articleId")
    private Collection<Comment> commentCollection;

    public void addComment(Comment newComment){
        commentCollection.add(newComment);
    }
    public Article() {
    }

    public Article(Long id) {
        this.id = id;
    }

    public Article(String title, String shortDescription, Date dateCreate, String status, String content, User email) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.dateCreate = dateCreate;
        this.status = status;
        this.content = content;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getEmail() {
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
    }

    public String getDateCreateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
        String dateString = sdf.format(dateCreate);
        return dateString;
    }

    public int getNumberComment() {
        return commentCollection.size();
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
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
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Article[ id=" + id + " ]";
    }

}
