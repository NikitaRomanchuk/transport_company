package ru.itis.romanchuk.transportcompany.web.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.romanchuk.transportcompany.api.dto.PathDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.PathForm;
import ru.itis.romanchuk.transportcompany.api.services.PathService;

import java.util.List;

@RestController
public class PathRestController {
    private final PathService pathService;

    public PathRestController(PathService pathService) {
        this.pathService = pathService;
    }

    @GetMapping("/api/admin/paths")
    public ResponseEntity<List<PathDto>> getPaths(){
        return ResponseEntity.ok(pathService.getPaths());
    }

    @PostMapping("/api/admin/paths")
    public HttpStatus addPath(@RequestBody PathForm pathForm){
        pathService.addPath(pathForm);
        return HttpStatus.OK;
    }

    @DeleteMapping("/api/admin/paths")
    public HttpStatus deleteCity(@RequestBody Long pathId){
        pathService.removePath(pathId);
        return HttpStatus.OK;
    }
}
