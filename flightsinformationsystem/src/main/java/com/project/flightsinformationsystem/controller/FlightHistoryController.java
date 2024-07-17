package com.project.flightsinformationsystem.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.project.flightsinformationsystem.dto.FlightHistoryDto;
import com.project.flightsinformationsystem.entities.City;
import com.project.flightsinformationsystem.entities.Flight;
import com.project.flightsinformationsystem.entities.FlightHistory;
import com.project.flightsinformationsystem.repositories.CityRepository;
import com.project.flightsinformationsystem.repositories.FlightHistoryRepository;
import com.project.flightsinformationsystem.repositories.FlightRepository;

@RestController
@RequestMapping("/FlightHistory")
public class FlightHistoryController {

    @Autowired
    private FlightHistoryRepository fhRepo;

    @Autowired
    private CityRepository cRepo;

    @Autowired
    private FlightRepository fRepo;

    @PostMapping("/")
    public FlightHistoryDto addFlightHistory(@RequestParam("fromCity") String fromCity, @RequestParam("toCity") String toCity,@RequestParam("flightNo") String flight,@RequestBody FlightHistory flightHistory)
    {
        try{
        FlightHistory f=new FlightHistory();
        City fromCityCode=cRepo.findById(fromCity).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"City Code Not Found"));
        City toCityCode =cRepo.findById(toCity).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"City Code Not Found"));
        f.setFromCity(fromCityCode);
        f.setToCity(toCityCode);
        Flight flightnumber=fRepo.findById(flight).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Flight No not found"));
        f.setFlight(flightnumber);
        int arrHour=flightHistory.getArrivalTime().getHour();
        int arrMin=flightHistory.getArrivalTime().getMinute();
        int deptHour=flightHistory.getDepartureTime().getHour();
        int deptMin=flightHistory.getDepartureTime().getMinute();           
        int duration=(arrHour-deptHour)*60+(arrMin-deptMin);
        f.setDurationInMins(duration); 
        LocalDate departurDate=flightHistory.getDepartureDate();
        LocalDate arrivalDate=flightHistory.getArrivalDate();
        if(departurDate.isEqual(arrivalDate))
        {
            f.setDepartureDate(departurDate);
            f.setArrivalDate(arrivalDate);
        }
        f.setDepartureTime(flightHistory.getDepartureTime());
        int departureHour=flightHistory.getDepartureTime().getHour();
        int departureMins=flightHistory.getDepartureTime().getMinute();
        int arrivalHour=flightHistory.getArrivalTime().getHour();
        int arrivalMins=flightHistory.getArrivalTime().getMinute();
        int arrivalInMins=arrivalHour*60+arrivalMins;
        int departureInMins=departureHour*60+departureMins;
        if(arrivalInMins>departureInMins)
         {  
             f.setArrivalTime(flightHistory.getArrivalTime());
         }
        else
           {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter Correct Details");
           }
        f.setAirCraft(flightHistory.getAirCraft());
        f.setRemarks(flightHistory.getRemarks());
        fhRepo.save(f);
        FlightHistoryDto fDto=new FlightHistoryDto();
        fDto.setFlightHistoryId(f.getFlightHistoryId());
        fDto.setDepartureDate(departurDate);
        fDto.setArrivalDate(arrivalDate);
        fDto.setDepartureTime(f.getDepartureTime());
        fDto.setArrivalTime(f.getArrivalTime());
        fDto.setDurationInMins(f.getDurationInMins());
        fDto.setAirCraft(f.getAirCraft());
        fDto.setFlight(flight);;
        fDto.setFromCity(fromCity);
        fDto.setToCity(toCity);
        fDto.setRemarks(f.getRemarks());
        return fDto;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input....");
        }
    }


    @DeleteMapping("/{flighthistoryid}")
    public String deleteFlightHistory(@PathVariable("flighthistoryid")long flightHistoryId )
    {
        Optional<FlightHistory> fh=fhRepo.findById(flightHistoryId);
        try{
        if(fh.isPresent())
        {
            fhRepo.deleteById(flightHistoryId);
            return "Flight History Data Deleted for "+flightHistoryId;
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Flight History Id Not Found.");
        }
    }
    catch(Exception e)
    {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"An Exception Occured While Deleting Data..");
    }
}
    

    @GetMapping("/{flightno}")
    public List<FlightHistoryDto> getFlightHistory(@PathVariable("flightno")String flightNo)
    {
        List<FlightHistory> flight=fhRepo.findByFlightFlightNo(flightNo);
        List<FlightHistoryDto> fhDto=new ArrayList<>();
        try{
            if(flight.isEmpty())
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data Found..");
            }
            for (FlightHistory f : flight) {
                FlightHistoryDto dto=new FlightHistoryDto();
                dto.setFlightHistoryId(f.getFlightHistoryId());
                dto.setFlight(f.getFlight().getFlightNo());
                dto.setFromCity(f.getFromCity().getCityCode());
                dto.setToCity(f.getToCity().getCityCode());
                dto.setDepartureDate(f.getDepartureDate());
                dto.setArrivalDate(f.getArrivalDate());
                dto.setDepartureTime(f.getDepartureTime());
                dto.setArrivalTime(f.getArrivalTime());
                dto.setDurationInMins(f.getDurationInMins());
                dto.setAirCraft(f.getAirCraft());
                dto.setRemarks(f.getRemarks());
                fhDto.add(dto);    
             }
             return fhDto;
            }
    catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An Exception Occured While Retreving Data.");
    }
}

    @GetMapping("/")
    public List<FlightHistoryDto> getFlightHistory(){      
        try{
        List<FlightHistory> allFlightHis=fhRepo.findAll(); 
        if(allFlightHis.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"data not availble");
        }
         List<FlightHistoryDto> flightHistoryDto=new ArrayList<>();
        for (FlightHistory a : allFlightHis) {
            FlightHistoryDto d=new FlightHistoryDto();
            d.setFlightHistoryId(a.getFlightHistoryId());
            d.setFlight(a.getFlight().getFlightNo());
            d.setFromCity(a.getFromCity().getCityCode());
            d.setToCity(a.getToCity().getCityCode());
            d.setDepartureDate(a.getDepartureDate());
            d.setDepartureTime(a.getDepartureTime());
            d.setArrivalDate(a.getArrivalDate());
            d.setArrivalTime(a.getArrivalTime());
            d.setDurationInMins(a.getDurationInMins());
            d.setAirCraft(a.getAirCraft());
            d.setRemarks(a.getRemarks());
            flightHistoryDto.add(d);      
        }
        return flightHistoryDto;
    }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An Exception Occured While Retreving Data");
        }
     }
     

    @GetMapping("/delayed-byMinutes")
    public List<FlightHistoryDto> getDelayedFlightHistoryByMinutes(@RequestParam("minutes") int minutes){
       
        List<FlightHistory> allFlights=fhRepo.findAllDelayedFlightHistory(minutes);
        List<FlightHistoryDto> fDto=new ArrayList<>();
        try{
        if(allFlights.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"data not availble");
        }
        for (FlightHistory a : allFlights) {
            FlightHistoryDto d=new FlightHistoryDto();
            d.setFlightHistoryId(a.getFlightHistoryId());
            d.setFlight(a.getFlight().getFlightNo());
            d.setFromCity(a.getFromCity().getCityCode());
            d.setToCity(a.getToCity().getCityCode());
            d.setDepartureDate(a.getDepartureDate());
            d.setDepartureTime(a.getDepartureTime());
            d.setArrivalDate(a.getArrivalDate());
            d.setArrivalTime(a.getArrivalTime());
            d.setDurationInMins(a.getDurationInMins());
            d.setAirCraft(a.getAirCraft());
            d.setRemarks(a.getRemarks());
            fDto.add(d);     
        }     
        return  fDto;
    }
    catch(Exception e){
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"An Exception Occured While Retreving Data.."+e.getMessage(),e);
        }
    }
   
}