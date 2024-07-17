package com.project.flightsinformationsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.flightsinformationsystem.entities.City;

public interface CityRepository extends JpaRepository<City,String>{
    
    City findByCityCode(String cityCode);


    List<City> findAllByCityCode(String cityCode);
    
    @Query("Select cityCode from City")
    List<String> findCityCode();
}
