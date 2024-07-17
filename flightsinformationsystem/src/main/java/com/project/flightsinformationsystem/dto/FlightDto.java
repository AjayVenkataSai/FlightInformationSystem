package com.project.flightsinformationsystem.dto;

import java.time.LocalTime;

public class FlightDto {

    private String flightNo;
    private String fromCity;
    private String toCity;
    private int durationInMins;
    private LocalTime departureTime;
    private LocalTime arrTime;
    private String airCraft;
    public FlightDto() {
    }
    public FlightDto(String flightNo, String fromCity, String toCity, int durationInMins, LocalTime departureTime,
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
    public String getFromCity() {
        return fromCity;
    }
    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }
    public String getToCity() {
        return toCity;
    }
    public void setToCity(String toCity) {
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
        return "FlightDto [flightNo=" + flightNo + ", fromCity=" + fromCity + ", toCity=" + toCity + ", durationInMins="
                + durationInMins + ", departureTime=" + departureTime + ", arrTime=" + arrTime + ", airCraft="
                + airCraft + "]";
    }
}
   