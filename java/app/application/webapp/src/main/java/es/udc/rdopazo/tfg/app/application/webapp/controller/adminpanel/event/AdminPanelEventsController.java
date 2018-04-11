package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.udc.rdopazo.tfg.app.application.webapp.util.WebInputValidation;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientEventAdmin;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientEventDayAdmin;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientEventPlaceAdmin;
import es.udc.rdopazo.tfg.app.util.exceptions.Config;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.dto.EventPersistDto;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlacePersistDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel/events")
public class AdminPanelEventsController {

    @Autowired
    ClientEventAdmin clientEventAdmin;

    @Autowired
    ClientEventDayAdmin clientEventDayAdmin;

    @Autowired
    ClientEventPlaceAdmin clientEventPlaceAdmin;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanelRoutes(HttpServletRequest request, Model model) {

        return "adminpanel/adminpanelevents";
    }

    @RequestMapping(path = "/ajax/event", method = RequestMethod.GET)
    public String routesFrag(HttpServletRequest request, @RequestParam(name = "filterBy") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InstanceNotFoundException, InputValidationException {

        String filterStr = WebInputValidation.valideOptionalEmpty(filter);
        String valueStr = WebInputValidation.valideOptionalEmpty(value);
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        Integer indexInt = 0;
        if (index.isPresent()) {
            indexInt = Integer.parseInt(index.get());
            model.addAttribute("index", Integer.parseInt(index.get()) + 1);
        } else {
            model.addAttribute("index", 1);
        }

        indexInt = indexInt * Config.PAGINATION;
        List<EventPersistDto> events = new ArrayList<>();

        events = this.clientEventAdmin.getService(token.getToken()).getAll(filterStr, valueStr, indexInt.toString(),
                Config.PAGINATION.toString());

        if (events.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }

        model.addAttribute("filterField", filterStr);
        model.addAttribute("filterValue", valueStr);
        model.addAttribute("events", events);
        return "fragments/adminpanel/adminpaneleventsfrag :: event";
    }

    @RequestMapping(path = "/ajax/event", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createRoute(HttpServletRequest request, @RequestBody EventPersistDto eventPersistDto,
            Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientEventAdmin.getService(token.getToken()).create(eventPersistDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @DeleteMapping("/ajax/{entity}/{id}")
    public ResponseEntity delete(HttpServletRequest request, @PathVariable(name = "entity") String entity,
            @PathVariable(name = "id") String id, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        switch (entity.toUpperCase()) {
        case "EVENT":
            try {
                this.clientEventAdmin.getService(token.getToken()).delete(id);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
            break;
        case "EVENTPLACE":
            try {
                this.clientEventPlaceAdmin.getService(token.getToken()).delete(id);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
            break;
        }

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/ajax/object/{entity}/{id}")
    public ResponseEntity<?> getById(HttpServletRequest request, @PathVariable(name = "entity") String entity,
            @PathVariable(name = "id") String id, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        switch (entity.toUpperCase()) {
        case "EVENT":
            try {
                return new ResponseEntity<EventPersistDto>(
                        this.clientEventAdmin.getService(token.getToken()).getById(id), null, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        case "EVENTPLACE":
            try {
                return new ResponseEntity<EventPlacePersistDto>(
                        this.clientEventPlaceAdmin.getService(token.getToken()).getById(id), null, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }
}
