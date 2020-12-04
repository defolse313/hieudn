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
import javax.persistence.Lob;
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
@Table(name = "Hotel", catalog = "Hotel", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h"),
    @NamedQuery(name = "Hotel.findByHotelId", query = "SELECT h FROM Hotel h WHERE h.hotelId = :hotelId"),
    @NamedQuery(name = "Hotel.findByHotelName", query = "SELECT h FROM Hotel h WHERE h.hotelName = :hotelName"),
    @NamedQuery(name = "Hotel.findByHotelArea", query = "SELECT h FROM Hotel h WHERE h.hotelArea = :hotelArea")})
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hotelId", nullable = false, length = 10)
    private String hotelId;
    @Basic(optional = false)
    @Column(name = "hotelName", nullable = false, length = 50)
    private String hotelName;
    @Basic(optional = false)
    @Column(name = "hotelArea", nullable = false, length = 50)
    private String hotelArea;
    @Basic(optional = false)
    @Lob
    @Column(name = "hotelImg", nullable = false, length = 2147483647)
    private String hotelImg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelId")
    private List<BookingDetails> bookingDetailsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelId")
    private List<Rooms> roomsList;

    public Hotel() {
    }

    public Hotel(String hotelId) {
        this.hotelId = hotelId;
    }

    public Hotel(String hotelId, String hotelName, String hotelArea, String hotelImg) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelArea = hotelArea;
        this.hotelImg = hotelImg;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelArea() {
        return hotelArea;
    }

    public void setHotelArea(String hotelArea) {
        this.hotelArea = hotelArea;
    }

    public String getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }

    @XmlTransient
    public List<BookingDetails> getBookingDetailsList() {
        return bookingDetailsList;
    }

    public void setBookingDetailsList(List<BookingDetails> bookingDetailsList) {
        this.bookingDetailsList = bookingDetailsList;
    }

    @XmlTransient
    public List<Rooms> getRoomsList() {
        return roomsList;
    }

    public void setRoomsList(List<Rooms> roomsList) {
        this.roomsList = roomsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelId != null ? hotelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.hotelId == null && other.hotelId != null) || (this.hotelId != null && !this.hotelId.equals(other.hotelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hieudn.entitites.Hotel[ hotelId=" + hotelId + " ]";
    }
    
}
