package ru.itis.romanchuk.transportcompany.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.romanchuk.transportcompany.api.dto.PathDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.CityForm;
import ru.itis.romanchuk.transportcompany.api.dto.forms.PathForm;
import ru.itis.romanchuk.transportcompany.api.services.CityService;
import ru.itis.romanchuk.transportcompany.api.services.PathService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PathController {
    private final PathService pathService;

    public PathController(PathService pathService) {
        this.pathService = pathService;
    }

    @GetMapping("/admin/paths")
    public String getView(Model model){
        model.addAttribute("cityList", pathService.getAvailableCities());
        model.addAttribute("pathList", pathService.getPaths());
        return "paths";
    }

    @PostMapping("/admin/paths")
    @ResponseBody
    public ResponseEntity<PathDto> addPath(PathForm pathForm){
        return ResponseEntity.ok(pathService.addPath(pathForm));
    }

    @DeleteMapping("/admin/paths")
    public void deleteCity(@RequestParam Long pathId, HttpServletResponse resp){
        pathService.removePath(pathId);
        resp.setStatus(200);
    }
}
