package com.project.flightsinformationsystem.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.project.flightsinformationsystem.dto.CityDto;
import com.project.flightsinformationsystem.entities.City;
import com.project.flightsinformationsystem.repositories.CityRepository;

@RestController
@RequestMapping("/City")
public class CityController {

    @Autowired
    private CityRepository cRepo;

    @PostMapping("/")
    public City addCity(@RequestBody City city)
    {  
        try{
            List<String> exists=cRepo.findCityCode();
            if(exists.contains(city.getCityCode()))
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"CityCode Already Exists..");
            }
            cRepo.save(city);
            return city;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid Input ....");
        }    
    }


    @DeleteMapping("/{cityCode}")
    public String deleteCityByCode(@PathVariable("cityCode")String cityCode)
    {
            try{
            City city=cRepo.findByCityCode(cityCode);
            if(city==null)
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CityCode Not Found");
            }
                cRepo.delete(city);
                return " City Data Deleted for "+ cityCode;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An Exception Occured While Deleting Data..");
        }
        }
    

    @GetMapping("/")
    public List<City>  getAll()
    {
    try{
        List<City> city=cRepo.findAll();
        if(city.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"City Data Not Found..");
         }
          return city;
    }
    catch(Exception e)
    {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"An Exception Occured While Retreving Data..");
    }
    }


    @PutMapping("/{citycode}")
    public City updateCity(@PathVariable("citycode")String cityCode,@RequestBody CityDto citydto)
    {
        City c=cRepo.findByCityCode(cityCode);
        List<String> s=cRepo.findCityCode();
        try{
        if(!s.contains(cityCode))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"City code Not Found..");
        }
        else{
        c.setCityName(citydto.getCityName());
        c.setCountry(citydto.getCountry());
        c.setMinsFromUtc(citydto.getMinsFromUtc());
        cRepo.save(c);
        return c;
        }
    }
    catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "An Exception Occured While Updating City..");
    }
    }
}
