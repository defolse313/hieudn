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
@Table(name = "BookingDetails", catalog = "Hotel", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookingDetails.findAll", query = "SELECT b FROM BookingDetails b"),
    @NamedQuery(name = "BookingDetails.findByBookingDetailsId", query = "SELECT b FROM BookingDetails b WHERE b.bookingDetailsId = :bookingDetailsId"),
    @NamedQuery(name = "BookingDetails.findByQuantity", query = "SELECT b FROM BookingDetails b WHERE b.quantity = :quantity"),
    @NamedQuery(name = "BookingDetails.findByAmountPrice", query = "SELECT b FROM BookingDetails b WHERE b.amountPrice = :amountPrice"),
    @NamedQuery(name = "BookingDetails.findByCheckIn", query = "SELECT b FROM BookingDetails b WHERE b.checkIn = :checkIn"),
    @NamedQuery(name = "BookingDetails.findByCheckOut", query = "SELECT b FROM BookingDetails b WHERE b.checkOut = :checkOut")})
public class BookingDetails implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookingDetailsId")
    private List<Feedback> feedbackList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "bookingDetailsId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingDetailsId;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "amountPrice", nullable = false)
    private int amountPrice;
    @Basic(optional = false)
    @Column(name = "checkIn", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkIn;
    @Basic(optional = false)
    @Column(name = "checkOut", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOut;
    @JoinColumn(name = "bookId", referencedColumnName = "bookId", nullable = false)
    @ManyToOne(optional = false)
    private Booking bookId;
    @JoinColumn(name = "hotelId", referencedColumnName = "hotelId", nullable = false)
    @ManyToOne(optional = false)
    private Hotel hotelId;
    @JoinColumn(name = "roomId", referencedColumnName = "roomId", nullable = false)
    @ManyToOne(optional = false)
    private Rooms roomId;

    public BookingDetails() {
    }

    public BookingDetails(Integer bookingDetailsId) {
        this.bookingDetailsId = bookingDetailsId;
    }

    public BookingDetails(Integer bookingDetailsId, int quantity, int amountPrice, Date checkIn, Date checkOut) {
        this.bookingDetailsId = bookingDetailsId;
        this.quantity = quantity;
        this.amountPrice = amountPrice;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getBookingDetailsId() {
        return bookingDetailsId;
    }

    public void setBookingDetailsId(Integer bookingDetailsId) {
        this.bookingDetailsId = bookingDetailsId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(int amountPrice) {
        this.amountPrice = amountPrice;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Booking getBookId() {
        return bookId;
    }

    public void setBookId(Booking bookId) {
        this.bookId = bookId;
    }

    public Hotel getHotelId() {
        return hotelId;
    }

    public void setHotelId(Hotel hotelId) {
        this.hotelId = hotelId;
    }

    public Rooms getRoomId() {
        return roomId;
    }

    public void setRoomId(Rooms roomId) {
        this.roomId = roomId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingDetailsId != null ? bookingDetailsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookingDetails)) {
            return false;
        }
        BookingDetails other = (BookingDetails) object;
        if ((this.bookingDetailsId == null && other.bookingDetailsId != null) || (this.bookingDetailsId != null && !this.bookingDetailsId.equals(other.bookingDetailsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entitites.BookingDetails[ bookingDetailsId=" + bookingDetailsId + " ]";
    }

    @XmlTransient
    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

}
