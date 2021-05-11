package ru.itis.romanchuk.transportcompany.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.romanchuk.transportcompany.api.dto.CityDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.CityForm;
import ru.itis.romanchuk.transportcompany.api.services.CityService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/admin/cities")
    public String getView(Model model){
        model.addAttribute("cityList", cityService.getCities());
        return "cities";
    }

    @PostMapping("/admin/cities")
    @ResponseBody
    public ResponseEntity<CityDto> addCity(CityForm cityForm, HttpServletResponse response){
        return ResponseEntity.ok(cityService.addCity(cityForm));
    }

    @DeleteMapping("/admin/cities")
    public void deleteCity(@RequestParam Long cityId, HttpServletResponse response){
        cityService.removeCity(cityId);
        response.setStatus(200);
    }
}
