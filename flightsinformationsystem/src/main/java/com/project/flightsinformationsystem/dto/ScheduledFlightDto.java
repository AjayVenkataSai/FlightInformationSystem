package com.project.flightsinformationsystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduledFlightDto {


     private long sheduledId;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String fromCity;
    private String toCity;
    private int durationMins;
    private String flight;
    private String status;
    public ScheduledFlightDto() {
    }
    public ScheduledFlightDto(long sheduledId, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate,
            LocalTime arrivalTime, String fromCity, String toCity, int durationMins, String flight,
            String status) {
        this.sheduledId = sheduledId;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.durationMins = durationMins;
        this.flight = flight;
        this.status = status;
    }
    public long getSheduledId() {
        return sheduledId;
    }
    public void setSheduledId(long sheduledId) {
        this.sheduledId = sheduledId;
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
    public int getDurationMins() {
        return durationMins;
    }
    public void setDurationMins(int durationMins) {
        this.durationMins = durationMins;
    }
    public String getFlight() {
        return flight;
    }
    public void setFlight(String flight) {
        this.flight = flight;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "ScheduledFlightDto [sheduledId=" + sheduledId + ", departureDate=" + departureDate + ", departureTime="
                + departureTime + ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime + ", fromCity="
                + fromCity + ", toCity=" + toCity + ", durationMins=" + durationMins + ", flight=" + flight
                + ", status=" + status + "]";
    } 
}
