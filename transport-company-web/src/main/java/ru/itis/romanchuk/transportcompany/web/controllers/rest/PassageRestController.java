package ru.itis.romanchuk.transportcompany.web.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.romanchuk.transportcompany.api.dto.PassageDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.PassageForm;
import ru.itis.romanchuk.transportcompany.api.services.PassageService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class PassageRestController {

    private final PassageService passageService;

    public PassageRestController(PassageService passageService) {
        this.passageService = passageService;
    }


    @GetMapping(value = "/api/passages", params = {"from", "to"})
    @ResponseBody
    public ResponseEntity<List<PassageDto>> getPassages(@RequestBody Long from, @RequestBody Long to){
        return ResponseEntity.ok(passageService.getByFromAndTo(from, to));
    }

    @GetMapping(value = "/api/passages")
    public ResponseEntity<List<PassageDto>> getPassages(){
        return ResponseEntity.ok(passageService.getPassages());
    }

    @PostMapping("/api/admin/passages")
    public HttpStatus addPassage(@RequestBody PassageForm passageForm){
        try {
            passageService.addPassage(passageForm);
        }catch (EntityNotFoundException e){
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }

    @DeleteMapping("/api/admin/passages")
    public HttpStatus deletePassage(@RequestBody Long passageId){
        try {
            passageService.removePassage(passageId);
        }catch (EntityNotFoundException e){
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.OK;
    }
}
