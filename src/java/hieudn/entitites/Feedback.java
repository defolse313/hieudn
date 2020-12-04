/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.entitites;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Feedback", catalog = "Hotel", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFeedBackId", query = "SELECT f FROM Feedback f WHERE f.feedBackId = :feedBackId"),
    @NamedQuery(name = "Feedback.findByRating", query = "SELECT f FROM Feedback f WHERE f.rating = :rating")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "feedBackId", nullable = false, length = 10)
    private String feedBackId;
    @Basic(optional = false)
    @Lob
    @Column(name = "feedBackContent", nullable = false, length = 2147483647)
    private String feedBackContent;
    @Basic(optional = false)
    @Column(name = "rating", nullable = false)
    private int rating;
    @JoinColumn(name = "bookingDetailsId", referencedColumnName = "bookingDetailsId", nullable = false)
    @ManyToOne(optional = false)
    private BookingDetails bookingDetailsId;
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    @ManyToOne(optional = false)
    private Users userId;

    public Feedback() {
    }

    public Feedback(String feedBackId) {
        this.feedBackId = feedBackId;
    }

    public Feedback(String feedBackId, String feedBackContent, int rating) {
        this.feedBackId = feedBackId;
        this.feedBackContent = feedBackContent;
        this.rating = rating;
    }

    public String getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(String feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getFeedBackContent() {
        return feedBackContent;
    }

    public void setFeedBackContent(String feedBackContent) {
        this.feedBackContent = feedBackContent;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public BookingDetails getBookingDetailsId() {
        return bookingDetailsId;
    }

    public void setBookingDetailsId(BookingDetails bookingDetailsId) {
        this.bookingDetailsId = bookingDetailsId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedBackId != null ? feedBackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedBackId == null && other.feedBackId != null) || (this.feedBackId != null && !this.feedBackId.equals(other.feedBackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entitites.Feedback[ feedBackId=" + feedBackId + " ]";
    }
    
}
