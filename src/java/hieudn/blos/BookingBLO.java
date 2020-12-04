/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.blos;

import hieudn.entitites.Booking;
import hieudn.entitites.Coupon;
import hieudn.entitites.Hotel;
import hieudn.entitites.Rooms;
import hieudn.entitites.Users;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hp
 */
public class BookingBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("BookingHotelPU");

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

    //    "SELECT d FROM Employee e JOIN e.department d"
    public List searchNameAndArea(String search, int currentPage, int pageMaxSize) throws Exception {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT h FROM Hotel h WHERE h.hotelName LIKE :hotelName OR h.hotelArea LIKE :hotelArea";
        Query query = em.createQuery(jpql);
        query.setParameter("hotelName", "%" + search + "%");
        query.setParameter("hotelArea", "%" + search + "%");
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List result = query.getResultList();
        return result;
    }

//    public List getHotel(Hotel search, int currentPage, int pageMaxSize) {
//        EntityManager em = emf.createEntityManager();
//        String jpql = "Hotel.findAll";
//        Query query = em.createNamedQuery(jpql);
//        query.setFirstResult((currentPage - 1) * pageMaxSize);
//        query.setMaxResults(pageMaxSize);
//        List result = query.getResultList();
//        return result;
//    }
    public int getAmount(String search, int pageMaxSize) throws Exception {
        EntityManager em = emf.createEntityManager();
        String sql = "select COUNT(hotelId) from Hotel where hotelName like ?";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter("1", "%" + search + "%");
        int count = (int) query.getSingleResult();
        em.getTransaction().commit();
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public List<Rooms> getRoomByHotelId(Hotel search, int currentPage, int pageMaxSize) throws Exception {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT r FROM Rooms r WHERE r.hotelId = :hotelId";
//        String sql = "SELECT roomId, amount, roomImg, roomTypeId, price, checkinDate, checkoutDate, hotelId FROM Rooms where hotelId = ?";
        Query query = em.createQuery(jpql);
        query.setParameter("hotelId", search);
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List result = query.getResultList();
        return result;
    }

    public int getAmountRoom(String search, int pageMaxSize) throws Exception {
        EntityManager em = emf.createEntityManager();
        String sql = "select COUNT(roomId) from Rooms where hotelId = ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, search);
        int count = (int) query.getSingleResult();
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public int getAmountBookedRoomsHotel(Hotel h) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT SUM(quantity) FROM BookingDetails WHERE hotelId = :hotelId";
        Query query = em.createQuery(jpql);
        query.setParameter("hotelId", h);
        int count = (int) query.getSingleResult();
        return count;
    }

    public int getAmountRoomsHotel(Hotel h) throws Exception {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT SUM(r.amount) FROM Rooms r WHERE r.hotelId = :hotelId";
        Query query = em.createQuery(jpql);
        query.setParameter("hotelId", h);
        long count = (long) query.getSingleResult();
        return (int) count;
    }

    public int getAmountHotelSearch(String search, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String sql = "Select COUNT(hotelId) FROM Hotel WHERE hotelName LIKE ? OR hotelArea LIKE ?";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, "%" + search + "%");
        query.setParameter(2, "%" + search + "%");
        int count = (int) query.getSingleResult();
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public List findRoom(Date checkinDate, Date checkoutDate, int amount) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT r FROM ROOM r WHERE (r.checkinDate < :checkinDate AND r.checkoutDate < :checkinDate) OR (r.checkinDate > :checkoutDate AND r.checkoutDate > :checkoutDate) AND r.amount = :amount";
        Query query = em.createQuery(jpql);
        query.setParameter("checkinDate", checkinDate);
        query.setParameter("checkoutDate", checkoutDate);
        query.setParameter("amount", amount);
        List result = query.getResultList();
        return result;
    }

    public List findRoomNoAmount(Date checkinDate, Date checkoutDate, int amount) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT r.hotelId FROM Rooms r WHERE ((r.checkinDate < :checkinDate AND r.checkoutDate < :checkinDate) OR (r.checkinDate > :checkoutDate AND r.checkoutDate > :checkoutDate))";
        Query query = em.createQuery(jpql);
        query.setParameter("checkinDate", checkinDate);
        query.setParameter("checkoutDate", checkoutDate);
        List result = query.getResultList();
        return result;
    }

    public List findRoomByDuc(Date checkinDate, Date checkoutDate, int amount) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT r.hotelId FROM Rooms r WHERE ((r.checkinDate < :checkinDate AND r.checkoutDate < :checkinDate) OR (r.checkinDate > :checkoutDate AND r.checkoutDate > :checkoutDate))";
        Query query = em.createQuery(jpql);
        query.setParameter("checkinDate", checkinDate);
        query.setParameter("checkoutDate", checkoutDate);
        List result = query.getResultList();
        return result;
    }

    public List getBookedAmount(Rooms r) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select b FROM BookingDetails b WHERE b.roomId = :roomId";
        Query query = em.createQuery(jpql);
        query.setParameter("roomId", r);
        List result = query.getResultList();
        return result;

    }

    public Rooms getRoomById(String roomId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Rooms.findByRoomId";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("roomId", roomId);
        Rooms r = (Rooms) query.getSingleResult();
        return r;
    }

    public int insert(Booking b) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
        return b.getBookId();

    }

    public Coupon getCoupon(String couponId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Coupon.findByCouponId";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("couponId", couponId);
        Coupon c = (Coupon) query.getSingleResult();
        return c;
    }

    public List searchBookingByName(Users userId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT b FROM Booking b WHERE b.userId = :userId AND b.status = 'active' order by b.bookTime DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("userId", userId);
        List b = query.getResultList();
        return b;
    }

    public List searchBookingByDate(Date date, Users userId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT b FROM Booking b WHERE b.bookTime = :bookTime AND b.userId = :userId AND b.status = 'active' order by b.bookTime DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("bookTime", date);
        query.setParameter("userId", userId);
        List b = query.getResultList();
        return b;
    }

    public boolean deleteBooking(String status, int bookId) {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        String sql = "UPDATE Booking SET status = ? WHERE bookId = ?";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter(1, status);
        query.setParameter(2, bookId);
        check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }
}
