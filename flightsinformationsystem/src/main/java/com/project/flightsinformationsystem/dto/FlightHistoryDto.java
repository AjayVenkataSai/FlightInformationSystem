package com.project.flightsinformationsystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class FlightHistoryDto {

    private long flightHistoryId;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String airCraft;
    private String remarks;
    private int durationInMins;
    private String flight;
    private String fromCity;
    private String toCity;
    public FlightHistoryDto() {
    }
    public FlightHistoryDto(long flightHistoryId, LocalDate departureDate, LocalTime departureTime,
            LocalDate arrivalDate, LocalTime arrivalTime, String airCraft, String remarks, int durationInMins,
            String flight, String fromCity, String toCity) {
        this.flightHistoryId = flightHistoryId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.airCraft = airCraft;
        this.remarks = remarks;
        this.durationInMins = durationInMins;
        this.flight = flight;
        this.fromCity = fromCity;
        this.toCity = toCity;
    }
    public long getFlightHistoryId() {
        return flightHistoryId;
    }
    public void setFlightHistoryId(long flightHistoryId) {
        this.flightHistoryId = flightHistoryId;
    }
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String getAirCraft() {
        return airCraft;
    }
    public void setAirCraft(String airCraft) {
        this.airCraft = airCraft;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public int getDurationInMins() {
        return durationInMins;
    }
    public void setDurationInMins(int durationInMins) {
        this.durationInMins = durationInMins;
    }
    public String getFlight() {
        return flight;
    }
    public void setFlight(String flight) {
        this.flight = flight;
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
    @Override
    public String toString() {
        return "FlightHistoryDto2 [flightHistoryId=" + flightHistoryId + ", departureDate=" + departureDate
                + ", departureTime=" + departureTime + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime
                + ", airCraft=" + airCraft + ", remarks=" + remarks + ", durationInMins=" + durationInMins + ", flight="
                + flight + ", fromCity=" + fromCity + ", toCity=" + toCity + "]";
    }

}
