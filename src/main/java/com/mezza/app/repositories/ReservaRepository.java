package com.mezza.app.repositories;

import com.mezza.app.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    /*
    List<Booking> findAllBookingsByDate(LocalDate date);

    List<Booking> getBookingByDateTimeAndCustomerId(LocalDate date, LocalTime time, Long customerId);

    List<Booking> getBookingsByCustomerId(Long customerId);

     */

}
