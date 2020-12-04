/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.entitites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Rooms", catalog = "Hotel", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByRoomId", query = "SELECT r FROM Rooms r WHERE r.roomId = :roomId"),
    @NamedQuery(name = "Rooms.findByPrice", query = "SELECT r FROM Rooms r WHERE r.price = :price"),
    @NamedQuery(name = "Rooms.findByAmount", query = "SELECT r FROM Rooms r WHERE r.amount = :amount"),
    @NamedQuery(name = "Rooms.findByStatus", query = "SELECT r FROM Rooms r WHERE r.status = :status")})
public class Rooms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "roomId", nullable = false, length = 10)
    private String roomId;
    @Basic(optional = false)
    @Lob
    @Column(name = "roomImg", nullable = false, length = 2147483647)
    private String roomImg;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private int price;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private int amount;
    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 10)
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private List<BookingDetails> bookingDetailsList;
    @JoinColumn(name = "roomTypeId", referencedColumnName = "roomTypeId", nullable = false)
    @ManyToOne(optional = false)
    private Category roomTypeId;
    @JoinColumn(name = "hotelId", referencedColumnName = "hotelId", nullable = false)
    @ManyToOne(optional = false)
    private Hotel hotelId;

    public Rooms() {
    }

    public Rooms(String roomId) {
        this.roomId = roomId;
    }

    public Rooms(String roomId, String roomImg, int price, int amount, String status) {
        this.roomId = roomId;
        this.roomImg = roomImg;
        this.price = price;
        this.amount = amount;
        this.status = status;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomImg() {
        return roomImg;
    }

    public void setRoomImg(String roomImg) {
        this.roomImg = roomImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public Category getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Category roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Hotel getHotelId() {
        return hotelId;
    }

    public void setHotelId(Hotel hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomId != null ? roomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.roomId == null && other.roomId != null) || (this.roomId != null && !this.roomId.equals(other.roomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entitites.Rooms[ roomId=" + roomId + " ]";
    }
    
}
