package com.project.flightsinformationsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.flightsinformationsystem.entities.FlightHistory;

public interface FlightHistoryRepository extends JpaRepository<FlightHistory,Long> {

    List<FlightHistory> findByFlightFlightNo(String flightNo);

    @Query(value=" select fh.* from flight_history fh JOIN flight f on fh.flight_no=f.flight_no where fh.duration_in_mins-f.duration_in_mins >0 And fh.duration_in_mins-f.duration_in_mins=:minutes",nativeQuery=true)
    List<FlightHistory> findAllDelayedFlightHistory(@Param("minutes") int minutes);

}
