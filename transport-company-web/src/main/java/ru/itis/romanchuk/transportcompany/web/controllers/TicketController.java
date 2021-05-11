package ru.itis.romanchuk.transportcompany.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.romanchuk.transportcompany.api.dto.TicketDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.TicketForm;
import ru.itis.romanchuk.transportcompany.api.services.CityService;
import ru.itis.romanchuk.transportcompany.api.services.TicketService;
import ru.itis.romanchuk.transportcompany.web.security.details.UserDetailsImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(value = "/ticket", params = {"passageId"})
    @ResponseBody
    public ResponseEntity<TicketDto> bookPassage(@RequestParam Long passageId,
                            @AuthenticationPrincipal UserDetailsImpl userDetails,
                            HttpServletResponse resp){
        TicketForm ticketForm = TicketForm.builder().passageId(passageId).userId(userDetails.getUser().getId()).build();
        return ResponseEntity.ok(ticketService.bookPassage(ticketForm));
    }

    @PostMapping(value = "/ticket", params = {"ticketId"})
    public void payForTicket(@RequestParam Long ticketId,
                             @AuthenticationPrincipal UserDetailsImpl userDetails,
                             HttpServletResponse resp){
        try {
            ticketService.payForTicket(ticketId, userDetails.getUser());
        }
        catch (IllegalArgumentException e){
            resp.setStatus(404);
            return;
        }
        resp.setStatus(200);
    }

}
