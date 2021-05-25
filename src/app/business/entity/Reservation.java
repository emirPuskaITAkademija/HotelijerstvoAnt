/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Grupa1
 */
@Entity
@Table(name = "reservation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findById", query = "SELECT r FROM Reservation r WHERE r.id = :id"),
    @NamedQuery(name = "Reservation.findByFromDate", query = "SELECT r FROM Reservation r WHERE r.fromDate = :fromDate"),
    @NamedQuery(name = "Reservation.findByToDate", query = "SELECT r FROM Reservation r WHERE r.toDate = :toDate"),
    @NamedQuery(name = "Reservation.findByCheckin", query = "SELECT r FROM Reservation r WHERE r.checkin = :checkin"),
    @NamedQuery(name = "Reservation.findByCheckinDateTime", query = "SELECT r FROM Reservation r WHERE r.checkinDateTime = :checkinDateTime"),
    @NamedQuery(name = "Reservation.findByCheckout", query = "SELECT r FROM Reservation r WHERE r.checkout = :checkout"),
    @NamedQuery(name = "Reservation.findByCheckoutDateTime", query = "SELECT r FROM Reservation r WHERE r.checkoutDateTime = :checkoutDateTime"),
    @NamedQuery(name = "Reservation.findByPrice", query = "SELECT r FROM Reservation r WHERE r.price = :price"),
    @NamedQuery(name = "Reservation.findByStatus", query = "SELECT r FROM Reservation r WHERE r.status = :status")})
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fromDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "toDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @Column(name = "checkin")
    private Short checkin;
    @Column(name = "checkin_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkinDateTime;
    @Column(name = "checkout")
    private Short checkout;
    @Column(name = "checkout_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkoutDateTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "status")
    private Integer status;
    @ManyToMany(mappedBy = "reservationList")
    private List<Guest> guestList;
    @JoinColumn(name = "id_guest", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Guest idGuest;
    @JoinColumn(name = "id_room", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Room idRoom;

    public Reservation() {
    }

    public Reservation(Integer id) {
        this.id = id;
    }

    public Reservation(Integer id, Date fromDate) {
        this.id = id;
        this.fromDate = fromDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Short getCheckin() {
        return checkin;
    }

    public void setCheckin(Short checkin) {
        this.checkin = checkin;
    }

    public Date getCheckinDateTime() {
        return checkinDateTime;
    }

    public void setCheckinDateTime(Date checkinDateTime) {
        this.checkinDateTime = checkinDateTime;
    }

    public Short getCheckout() {
        return checkout;
    }

    public void setCheckout(Short checkout) {
        this.checkout = checkout;
    }

    public Date getCheckoutDateTime() {
        return checkoutDateTime;
    }

    public void setCheckoutDateTime(Date checkoutDateTime) {
        this.checkoutDateTime = checkoutDateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @XmlTransient
    public List<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<Guest> guestList) {
        this.guestList = guestList;
    }

    public Guest getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(Guest idGuest) {
        this.idGuest = idGuest;
    }

    public Room getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Room idRoom) {
        this.idRoom = idRoom;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.business.entity.Reservation[ id=" + id + " ]";
    }
    
}
