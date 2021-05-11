package ru.itis.romanchuk.transportcompany.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.romanchuk.transportcompany.api.dto.PassageDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.PassageForm;
import ru.itis.romanchuk.transportcompany.api.services.PassageService;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PassageController {

    private final PassageService passageService;

    public PassageController(PassageService passageService) {
        this.passageService = passageService;
    }

    @GetMapping(value = "/passages")
    public String getView(Model model){
        model.addAttribute("cityList", passageService.availableCities());
        return "passages";
    }

    @GetMapping(value = "/passages/{id}")
    public String getView(Model model, @PathVariable Long id){
        model.addAttribute("passage", passageService.getById(id));
        model.addAttribute("cities", passageService.getOptimalPath(id));
        return "passage";
    }

    @GetMapping(value = "/passages", params = {"from", "to"})
    @ResponseBody
    public List<PassageDto> getPassages(@RequestParam Long from, @RequestParam Long to){
        return passageService.getByFromAndTo(from, to);
    }

    @GetMapping(value = "/admin/passages")
    public String getAdminView(Model model){
        model.addAttribute("passagesList", passageService.getPassages());
        model.addAttribute("cityList", passageService.availableCities());
        return "passageAdmin";
    }

    @PostMapping("/admin/passages")
    @ResponseBody
    public ResponseEntity<PassageDto> addPassage(PassageForm passageForm){
        try {
            return ResponseEntity.ok(passageService.addPassage(passageForm));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/admin/passages")
    public void deletePassage(@RequestParam Long passageId, HttpServletResponse resp){
        try {
            passageService.removePassage(passageId);
        }catch (EntityNotFoundException e){
            resp.setStatus(404);
            return;
        }
        resp.setStatus(200);
    }
}
