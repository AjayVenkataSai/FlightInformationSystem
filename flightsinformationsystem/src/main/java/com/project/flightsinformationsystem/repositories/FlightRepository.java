package com.project.flightsinformationsystem.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.project.flightsinformationsystem.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight,String> {

    List<Flight> findByFromCityCityCodeAndToCityCityCode(String fromCityCode, String toCityCode);

    
    @Query("select flightNo from Flight")
    List<String> getFlightNo();

    Flight findByFlightNo(String flightNo);


    Flight findAllByFlightNo(String flightNo);
}
