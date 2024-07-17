package com.project.flightsinformationsystem.dto;


public class CityDto {

    private String cityName;
    private String minsFromUtc;
    private String country;
    public CityDto() {
        
    }
    public CityDto(String cityName, String minsFromUtc, String country) {
        this.cityName = cityName;
        this.minsFromUtc = minsFromUtc;
        this.country = country;
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
        return "CityDto [cityName=" + cityName + ", minsFromUtc=" + minsFromUtc + ", country=" + country + "]";
    }    
}
