package ru.itis.romanchuk.transportcompany.web.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.romanchuk.transportcompany.api.dto.CityDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.CityForm;
import ru.itis.romanchuk.transportcompany.api.services.CityService;
import ru.itis.romanchuk.transportcompany.api.valid.HasNumber;

import java.util.List;

@RestController
public class CityRestController {

    private final CityService cityService;

    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/api/cities")
    public ResponseEntity<List<CityDto>> getCities(){
        return ResponseEntity.ok(cityService.getCities());
    }

    @PostMapping("/api/admin/cities")
    public HttpStatus addCity(@RequestBody CityForm cityForm){
        cityService.addCity(cityForm);
        return HttpStatus.OK;
    }

    @DeleteMapping("/api/admin/cities")
    public HttpStatus deleteCity(@RequestBody Long cityId){
        cityService.removeCity(cityId);
        return HttpStatus.OK;
    }
}
