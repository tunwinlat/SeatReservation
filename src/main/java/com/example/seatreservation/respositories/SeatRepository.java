package com.example.seatreservation.respositories;

import com.example.seatreservation.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
    List<Seat> findSeatById (int kw);

}

