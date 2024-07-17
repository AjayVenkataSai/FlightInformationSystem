package com.project.flightsinformationsystem.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.project.flightsinformationsystem.dto.FlightDto;
import com.project.flightsinformationsystem.entities.Flight;
import com.project.flightsinformationsystem.repositories.CityRepository;
import com.project.flightsinformationsystem.repositories.FlightRepository;

@RestController
@RequestMapping("/Flight")
public class FlightController {

    @Autowired
    private FlightRepository fRepo;

    @Autowired
    private CityRepository cRepo;

    @PostMapping("/")
    public FlightDto addFlight(@RequestBody Flight flight1,@RequestParam("fromcity")String fromCity,@RequestParam("toCity")String toCity)
    {
        try
        {
            String f=flight1.getFlightNo();
            List<String> allFlights=fRepo.getFlightNo();
            if(allFlights.contains(f))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight Number Already Exists");
            }   
            Flight flight=new Flight();
            flight.setFlightNo(flight1.getFlightNo());
            flight.setFromCity(cRepo.findByCityCode(fromCity));
            flight.setToCity(cRepo.findByCityCode(toCity));
            flight.setDepartureTime(flight1.getDepartureTime());
            int departureHour=flight1.getDepartureTime().getHour();
            int departureMins=flight1.getDepartureTime().getMinute();
            int arrivalHour=flight1.getArrTime().getHour();
            int arrivalMins=flight1.getArrTime().getMinute();
            int arrivalInMins=arrivalHour*60+arrivalMins;
            int departureInMins=departureHour*60+departureMins;
            if(arrivalInMins>departureInMins)
            {  
              flight.setArrTime(flight1.getArrTime());
            }
            else
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter Correct Details");
            }
            int arrHour=flight1.getArrTime().getHour();
            int arrMin=flight1.getArrTime().getMinute();
            int deptHour=flight1.getDepartureTime().getHour();
            int deptMin=flight1.getDepartureTime().getMinute();
            int duration=(arrHour-deptHour)*60+(arrMin-deptMin);
            flight.setDurationInMins(duration);           
            flight.setAirCraft(flight1.getAirCraft());
            fRepo.save(flight);
            FlightDto fDto=new FlightDto();
            fDto.setFlightNo(flight.getFlightNo());
            fDto.setFromCity(flight.getFromCity().getCityCode());
            fDto.setToCity(flight.getToCity().getCityCode());
            fDto.setDepartureTime(flight.getDepartureTime());
            fDto.setArrTime(flight.getArrTime());
            fDto.setDurationInMins(duration);
            fDto.setAirCraft(flight.getAirCraft());
            return fDto;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input...");
        }
    }

    @GetMapping("/{pagenum}/{size}")
    public List<FlightDto> getFlights(@PathVariable("pagenum")int pagenum,@PathVariable("size")int size) 
    {
        try{
        List<FlightDto> flightDtos=new ArrayList<>();
        PageRequest page=PageRequest.of(pagenum,size);
        Page<Flight> flight=fRepo.findAll(page);
        if(flight.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Flight Data Not Found...");
        }
        for (Flight flights : flight) 
        {
            FlightDto flightDto=new FlightDto();
            flightDto.setFlightNo(flights.getFlightNo());
            flightDto.setFromCity(flights.getFromCity().getCityCode());
            flightDto.setToCity(flights.getToCity().getCityCode());
            flightDto.setDepartureTime(flights.getDepartureTime());
            flightDto.setArrTime(flights.getArrTime());
            flightDto.setDurationInMins(flights.getDurationInMins());
            flightDto.setAirCraft(flights.getAirCraft());
            flightDtos.add(flightDto);
        }
        return flightDtos;
    }
    catch(Exception e)
    {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An Exception Occured While Retreving Data..");
    }
}

    @GetMapping("/details/{fromcity}/{tocity}")
    public List<FlightDto> getflights(@PathVariable("fromcity")String fromCity,@PathVariable("tocity")String toCity)
    {
        try
        {
            List<Flight> flight=fRepo.findByFromCityCityCodeAndToCityCityCode(fromCity, toCity);
            List<FlightDto> flightDtos=new ArrayList<>();
            if(flight.isEmpty())
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight Data Not Found..");
            }
            for (Flight f : flight) {
                FlightDto flightDto=new FlightDto();
                flightDto.setFlightNo(f.getFlightNo());
                flightDto.setFromCity(fromCity);
                flightDto.setToCity(toCity);
                flightDto.setDepartureTime(f.getDepartureTime());
                flightDto.setArrTime(f.getArrTime());
                flightDto.setDurationInMins(f.getDurationInMins());
                flightDto.setAirCraft(f.getAirCraft());
                flightDtos.add(flightDto);
            }
            return flightDtos;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Failed to Retrieve Flights Data..");
        }
    }


    @DeleteMapping("/{flightNo}")
    public String deleteFlight(@PathVariable("flightNo")String flightNo)
    {
        try
        {
            Flight flight=fRepo.findByFlightNo(flightNo);
        if(flight==null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Flight no Not Found");
        }
        else
        {
            fRepo.deleteById(flightNo);
            return "Flight Data Deleted for "+ flightNo;
        }
    }  
    catch(Exception e)  
    {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An Exception Occured While Deleting Data...");
    } 

}
}