package com.project.flightsinformationsystem.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ScheduledFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sheduled_id")
    @JsonIgnore
    private long sheduledId;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;

    @ManyToOne
    @JoinColumn(name="from_city_code")
    @JsonIgnore
    private City fromCity;

    @ManyToOne
    @JoinColumn(name="to_city_code")
    @JsonIgnore
    private City toCity;
    @Column(name="duration_in_mins")
    @JsonIgnore
    private int durationMins;
   
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="flight_no") 
    private Flight flight;
    
    @JsonIgnore
    @Column(name="status",nullable = false,columnDefinition="varchar(255)DEFAULT'Active'")
    private String status;


    public ScheduledFlight() {

    }


    public ScheduledFlight(LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
            City fromCity, City toCity, int durationMins, Flight flight,String status) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.durationMins = durationMins;
        this.flight = flight;
        this.status=status;
    }


    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }


    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }


    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }


    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }


    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }


    public void setToCity(City toCity) {
        this.toCity = toCity;
    }


    public void setDurationMins(int durationMins) {
        this.durationMins = durationMins;
    }


    public void setFlight(Flight flight) {
        this.flight = flight;
    }


    public long getSheduledId() {
        return sheduledId;
    }


    public LocalDate getDepartureDate() {
        return departureDate;
    }


    public LocalTime getDepartureTime() {
        return departureTime;
    }


    public LocalDate getArrivalDate() {
        return arrivalDate;
    }


    public LocalTime getArrivalTime() {
        return arrivalTime;
    }


    public City getFromCity() {
        return fromCity;
    }


    public City getToCity() {
        return toCity;
    }


    public int getDurationMins() {
        return durationMins;
    }


    public Flight getFlight() {
        return flight;
    }

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "ScheduledFlight [sheduledId=" + sheduledId + ", departureDate=" + departureDate + ", departureTime="
                + departureTime + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime + ", fromCity="
                + fromCity + ", toCity=" + toCity + ", durationMins=" + durationMins + ", flight=" + flight
                + ", status=" + status + "]";
    }

    

}