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
public class FlightHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flight_history_id")
    @JsonIgnore
    private long flightHistoryId;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    @JsonIgnore
    private int durationInMins;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="from_city_code")
    private City fromCity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="to_city_code")
    private City toCity;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="flight_no")
    private Flight flight;

    private String airCraft;
    private String remarks;

    public FlightHistory(){

    }

    public FlightHistory(LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime,
            int durationInMins, City fromCity, City toCity, String airCraft, String remarks, Flight flight) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.durationInMins = durationInMins;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.airCraft = airCraft;
        this.remarks = remarks;
        this.flight = flight;
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

    public void setDurationInMins(int durationInMins) {
        this.durationInMins = durationInMins;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public void setAirCraft(String airCraft) {
        this.airCraft = airCraft;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    public long getFlightHistoryId() {
        return flightHistoryId;
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

    public int getDurationInMins() {
        return durationInMins;
    }

    public City getFromCity() {
        return fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public String getAirCraft() {
        return airCraft;
    }

    public String getRemarks() {
        return remarks;
    }

    public Flight getFlight() {
        return flight;
    }

    @Override
    public String toString() {
        return "FlightHistory [flightHistoryId=" + flightHistoryId + ", departureDate=" + departureDate
                + ", departureTime=" + departureTime + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime
                + ", durationInMins=" + durationInMins + ", fromCity=" + fromCity + ", toCity=" + toCity + ", airCraft="
                + airCraft + ", remarks=" + remarks + ", flight=" + flight + "]";
    }

   
}