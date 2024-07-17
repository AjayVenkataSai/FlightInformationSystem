package com.project.flightsinformationsystem.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class City {

    @Id
    @Column(name="city_code",unique = true)
    private String cityCode;
    @Column(name="city_name")
    private String cityName;
    @Column(name="mins_from_utc")
    private String minsFromUtc;
    private String country;
    
    @JsonIgnore
    @OneToMany(mappedBy = "fromCity",cascade = CascadeType.ALL)
    private List<Flight> flightComingFrom;
    
    @JsonIgnore
    @OneToMany(mappedBy = "toCity",cascade = CascadeType.ALL)
    private List<Flight> flightGoingTo;

    public City() {
    }

    public City(String cityCode, String cityName, String minsFromUtc, String country) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.minsFromUtc = minsFromUtc;
        this.country = country;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMinsFromUtc() {
        return minsFromUtc;
    }

    public void setMinsFromUtc(String minsFromUtc) {
        this.minsFromUtc = minsFromUtc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City [cityCode=" + cityCode + ", cityName=" + cityName + ", minsFromUtc=" + minsFromUtc + ", country="
                + country + "]";
    }
}