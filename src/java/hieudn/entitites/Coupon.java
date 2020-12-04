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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "Coupon", catalog = "Hotel", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coupon.findAll", query = "SELECT c FROM Coupon c"),
    @NamedQuery(name = "Coupon.findByCouponId", query = "SELECT c FROM Coupon c WHERE c.couponId = :couponId"),
    @NamedQuery(name = "Coupon.findByDiscountValue", query = "SELECT c FROM Coupon c WHERE c.discountValue = :discountValue"),
    @NamedQuery(name = "Coupon.findByExpiredDate", query = "SELECT c FROM Coupon c WHERE c.expiredDate = :expiredDate")})
public class Coupon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "couponId", nullable = false, length = 10)
    private String couponId;
    @Basic(optional = false)
    @Column(name = "discountValue", nullable = false)
    private double discountValue;
    @Basic(optional = false)
    @Column(name = "expiredDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;
    @OneToMany(mappedBy = "couponId")
    private List<Booking> bookingList;

    public Coupon() {
    }

    public Coupon(String couponId) {
        this.couponId = couponId;
    }

    public Coupon(String couponId, double discountValue, Date expiredDate) {
        this.couponId = couponId;
        this.discountValue = discountValue;
        this.expiredDate = expiredDate;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    @XmlTransient
    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (couponId != null ? couponId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coupon)) {
            return false;
        }
        Coupon other = (Coupon) object;
        if ((this.couponId == null && other.couponId != null) || (this.couponId != null && !this.couponId.equals(other.couponId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entitites.Coupon[ couponId=" + couponId + " ]";
    }
    
}
