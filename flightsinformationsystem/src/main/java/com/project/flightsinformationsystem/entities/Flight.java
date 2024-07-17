package com.project.flightsinformationsystem.entities;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Flight {

    @Id
    @Column(name="flight_no",unique=true)
    private String flightNo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="from_city_code")
    private City fromCity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="to_city_code")
    private City toCity;

    @JsonIgnore
    private int durationInMins;
    private LocalTime departureTime;
    @Column(name="arrival_time")
    private LocalTime arrTime;
    private String airCraft;
    @JsonIgnore
    @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL)
    private List<ScheduledFlight> sheduledFlight;
    @JsonIgnore
    @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL)
    private List<FlightHistory> flightHistory;

    public Flight() {
    }

    public Flight(String flightNo, City fromCity, City toCity, int durationInMins, LocalTime departureTime,
            LocalTime arrTime, String airCraft) {
        this.flightNo = flightNo;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.durationInMins = durationInMins;
        this.departureTime = departureTime;
        this.arrTime = arrTime;
        this.airCraft = airCraft;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public City getFromCity() {
        return fromCity;
    }

    public void setFromCity(City fromCity) {
        this.fromCity = fromCity;
    }

    public City getToCity() {
        return toCity;
    }

    public void setToCity(City toCity) {
        this.toCity = toCity;
    }

    public int getDurationInMins() {
        return durationInMins;
    }

    public void setDurationInMins(int durationInMins) {
        this.durationInMins = durationInMins;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrTime() {
        return arrTime;
    }

    public void setArrTime(LocalTime arrTime) {
        this.arrTime = arrTime;
    }

    public String getAirCraft() {
        return airCraft;
    }

    public void setAirCraft(String airCraft) {
        this.airCraft = airCraft;
    }

    @Override
    public String toString() {
        return "Flight [flightNo=" + flightNo + ", fromCity=" + fromCity + ", toCity=" + toCity + ", durationInMins="
                + durationInMins + ", departureTime=" + departureTime + ", arrTime=" + arrTime + ", airCraft="
                + airCraft + "]";
    }

}