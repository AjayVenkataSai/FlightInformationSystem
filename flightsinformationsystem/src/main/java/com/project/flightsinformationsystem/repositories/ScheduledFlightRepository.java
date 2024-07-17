package com.project.flightsinformationsystem.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.flightsinformationsystem.entities.ScheduledFlight;


public interface ScheduledFlightRepository extends JpaRepository<ScheduledFlight,Long>{

    List<ScheduledFlight> findByDepartureDateBetween(LocalDate departurDate1, LocalDate departurDate2);

    List<ScheduledFlight> findAllByDepartureDateAndFromCityCityCode(LocalDate departureDate, String fromCity);

}
