package com.project.flightsinformationsystem.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.project.flightsinformationsystem.dto.ScheduledFlightDto;
import com.project.flightsinformationsystem.entities.City;
import com.project.flightsinformationsystem.entities.Flight;
import com.project.flightsinformationsystem.entities.ScheduledFlight;
import com.project.flightsinformationsystem.repositories.CityRepository;
import com.project.flightsinformationsystem.repositories.FlightRepository;
import com.project.flightsinformationsystem.repositories.ScheduledFlightRepository;
@RestController
@RequestMapping("/SheduledFlight")
public class ScheduledFlightController {

    @Autowired
    private ScheduledFlightRepository sfRepo;
    
    @Autowired
    private FlightRepository fRepo;

    @Autowired
    private CityRepository cRepo;
               

     @PostMapping("/")
     public ScheduledFlightDto addScheduleFlight(@RequestParam("fromCity") String fromCity,
                                                    @RequestParam("toCity") String toCity,
                                                    @RequestParam("flight") String flight,
                                                    @RequestBody ScheduledFlight scheduledFlight1) {
        try {
            City fromCityCode = cRepo.findById(fromCity)
                                     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"From City Not Found"));
            City toCityCode = cRepo.findById(toCity)
                                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"To City Not Found"));
            Flight flightnumber = fRepo.findById(flight)
                                       .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight No Not Found"));

            ScheduledFlight s = new ScheduledFlight();
            s.setFromCity(fromCityCode);
            s.setToCity(toCityCode);
            s.setFlight(flightnumber);
            int arrHour=scheduledFlight1.getArrivalTime().getHour();
            int arrMins=scheduledFlight1.getArrivalTime().getMinute();
            int depHour=scheduledFlight1.getDepartureTime().getHour();
            int depMin=scheduledFlight1.getDepartureTime().getMinute();
            int duration=(arrHour-depHour)*60+(arrMins-depMin);
            s.setDurationMins(duration);
            s.setDepartureTime(scheduledFlight1.getDepartureTime());
            int departureHour=scheduledFlight1.getDepartureTime().getHour();
            int departureMins=scheduledFlight1.getDepartureTime().getMinute();
            int arrivalHour=scheduledFlight1.getArrivalTime().getHour();
            int arrivalMins=scheduledFlight1.getArrivalTime().getMinute();
            int arrivalInMins=arrivalHour*60+arrivalMins;
            int departureInMins=departureHour*60+departureMins;
            if(arrivalInMins>departureInMins)
             {  
                 s.setArrivalTime(scheduledFlight1.getArrivalTime());
             }
              else
               {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter Correct Details");
                }
            LocalDate departurDate=scheduledFlight1.getDepartureDate();
            LocalDate arrivalDate=scheduledFlight1.getArrivalDate();
            if(departurDate.isEqual(arrivalDate))
            {
                s.setDepartureDate(departurDate);
                s.setArrivalDate(arrivalDate);
            }
            s.setStatus("Active");
            sfRepo.save(s);
            ScheduledFlightDto sfDto=new ScheduledFlightDto();
            sfDto.setSheduledId(s.getSheduledId());
            sfDto.setDepartureDate(s.getDepartureDate());
            sfDto.setArrivalDate(s.getArrivalDate());
            sfDto.setDepartureTime(s.getDepartureTime());
            sfDto.setArrivalTime(s.getArrivalTime());
            sfDto.setDurationMins(s.getDurationMins());
            sfDto.setFromCity(fromCity);
            sfDto.setToCity(toCity);
            sfDto.setFlight(flight);
            sfDto.setStatus(s.getStatus());
            return sfDto;
        } 
         catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input...");
        }
    }



    @PostMapping("/addScheduledFlights")
    public ScheduledFlightDto addFlightUponTwodates(@RequestParam("departureDate") LocalDate arrivalDate,
                                       @RequestParam("arrivalDate") LocalDate departurDate,
                                       @RequestParam("flightNo") String flightNo ){
    try{
    Flight allFlight=fRepo.findAllByFlightNo(flightNo);
    Flight f=fRepo.findById(allFlight.getFlightNo()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Flight No Not Found"));
    ScheduledFlight sc=new ScheduledFlight();
    sc.setArrivalDate(arrivalDate);
    sc.setDepartureDate(departurDate);
    sc.setArrivalTime(allFlight.getArrTime());
    sc.setDepartureTime(allFlight.getDepartureTime());
    sc.setFromCity(allFlight.getFromCity());
    sc.setToCity(allFlight.getToCity());
    sc.setFlight(f);
    sc.setDurationMins(allFlight.getDurationInMins()); 
    sc.setStatus("Active");
    sfRepo.save(sc);
    ScheduledFlightDto sfDto=new ScheduledFlightDto();
    sfDto.setSheduledId(sc.getSheduledId());
    sfDto.setDepartureDate(departurDate);
    sfDto.setArrivalDate(arrivalDate);
    sfDto.setDepartureTime(sc.getDepartureTime());
    sfDto.setArrivalTime(sc.getArrivalTime());
    sfDto.setDurationMins(sc.getDurationMins());
    sfDto.setFromCity(sc.getFromCity().getCityCode());
    sfDto.setToCity(sc.getToCity().getCityCode());
    sfDto.setFlight(flightNo);
    sfDto.setStatus(sc.getStatus());
    return sfDto;
    }
    catch(Exception e)
    {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input..");

    }
}


    @PutMapping("/cancelScheduledFlights/{departureDate1}/{departureDate2}")
    public List<ScheduledFlightDto> cancelScheduleFlightByDates(@PathVariable("departureDate1") LocalDate departureDate1,@PathVariable("departureDate2") LocalDate departurDate2){
        try{ 
        List<ScheduledFlight> schFlight=sfRepo.findByDepartureDateBetween(departureDate1,departurDate2);
        if(schFlight.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Data not availble");
        }
       List<ScheduledFlightDto> sDto=new ArrayList<>();
       List<ScheduledFlight> cancelledFlights=new ArrayList<>();
        for (ScheduledFlight s : schFlight) {
            s.setStatus("cancelled");
            sfRepo.save(s); 
            ScheduledFlightDto sfDto=new ScheduledFlightDto();
            sfDto.setSheduledId(s.getSheduledId());
            sfDto.setDepartureDate(s.getDepartureDate());
            sfDto.setArrivalDate(s.getArrivalDate());  
            sfDto.setDepartureTime(s.getDepartureTime());
            sfDto.setArrivalTime(s.getArrivalTime());
            sfDto.setFromCity(s.getFromCity().getCityCode());
            sfDto.setToCity(s.getToCity().getCityCode());
            sfDto.setFlight(s.getFlight().getFlightNo());
            sfDto.setStatus(s.getStatus());
            sDto.add(sfDto);
            if("cancelled".equals(s.getStatus()))
            {
                cancelledFlights.add(s);
            }
        }
        sfRepo.deleteAll(cancelledFlights);
        return sDto;
    }
    catch(Exception e){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"An internal server occured "+e.getMessage(),e);
    }
}


    @GetMapping("/{departuredate}/{fromcity}")
    public List<ScheduledFlightDto> gScheduledFlightsByDateAndFromCity(@PathVariable("departuredate") LocalDate departureDate,
                                                                    @PathVariable("fromcity") String fromCity){
    try{
     List<ScheduledFlight> schFlight=sfRepo.findAllByDepartureDateAndFromCityCityCode(departureDate,fromCity);
     List<ScheduledFlightDto> schDto=new ArrayList<>();
     if(schFlight.isEmpty()){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Data not availble");
    }
     for (ScheduledFlight a : schFlight) {
        ScheduledFlightDto ad=new ScheduledFlightDto();
        ad.setFlight(a.getFlight().getFlightNo());
        ad.setFromCity(a.getFromCity().getCityCode());
        ad.setToCity(a.getToCity().getCityCode());
        ad.setSheduledId(a.getSheduledId());
        ad.setArrivalDate(a.getArrivalDate());
        ad.setArrivalTime(a.getArrivalTime());
        ad.setDepartureDate(a.getDepartureDate());
        ad.setDepartureTime(a.getDepartureTime());
        ad.setDurationMins(a.getDurationMins());
        ad.setStatus(a.getStatus());
        schDto.add(ad);
     }
     return schDto;
    }
     catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"An Exception Occured While Retreving Data.. "+e.getMessage(),e);
    }
}
}
