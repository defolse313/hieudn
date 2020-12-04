/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.entitites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author hp
 */
@Entity
@Table(name = "Booking", catalog = "Hotel", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findByBookId", query = "SELECT b FROM Booking b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "Booking.findByBookTime", query = "SELECT b FROM Booking b WHERE b.bookTime = :bookTime"),
    @NamedQuery(name = "Booking.findByTotalPrice", query = "SELECT b FROM Booking b WHERE b.totalPrice = :totalPrice"),
    @NamedQuery(name = "Booking.findByStatus", query = "SELECT b FROM Booking b WHERE b.status = :status")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "bookId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    @Basic(optional = false)
    @Column(name = "bookTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookTime;
    @Basic(optional = false)
    @Column(name = "totalPrice", nullable = false)
    private double totalPrice;
    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 10)
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookId")
    private List<BookingDetails> bookingDetailsList;
    @JoinColumn(name = "couponId", referencedColumnName = "couponId", nullable = true)
    @ManyToOne
    private Coupon couponId;
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    @ManyToOne(optional = false)
    private Users userId;

    public Booking() {
    }

    public Booking(Integer bookId) {
        this.bookId = bookId;
    }

    public Booking(Integer bookId, Date bookTime, double totalPrice, String status) {
        this.bookId = bookId;
        this.bookTime = bookTime;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Booking(Integer bookId, Date bookTime, double totalPrice, String status, Coupon couponId, Users userId) {
        this.bookId = bookId;
        this.bookTime = bookTime;
        this.totalPrice = totalPrice;
        this.status = status;
        this.couponId = couponId;
        this.userId = userId;
    }

    public Booking(Integer bookId, Date bookTime, double totalPrice, String status, Users userId) {
        this.bookId = bookId;
        this.bookTime = bookTime;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getBookTime() {
        return bookTime;
    }

    public void setBookTime(Date bookTime) {
        this.bookTime = bookTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<BookingDetails> getBookingDetailsList() {
        return bookingDetailsList;
    }

    public void setBookingDetailsList(List<BookingDetails> bookingDetailsList) {
        this.bookingDetailsList = bookingDetailsList;
    }

    public Coupon getCouponId() {
        return couponId;
    }

    public void setCouponId(Coupon couponId) {
        this.couponId = couponId;
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
        hash += (bookId != null ? bookId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.bookId == null && other.bookId != null) || (this.bookId != null && !this.bookId.equals(other.bookId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entitites.Booking[ bookId=" + bookId + " ]";
    }

}
