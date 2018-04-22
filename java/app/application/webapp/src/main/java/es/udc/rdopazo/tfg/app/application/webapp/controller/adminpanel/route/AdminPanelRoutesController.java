package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel.route;

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
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientRouteAdmin;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientRouteDayAdmin;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientStayAdmin;
import es.udc.rdopazo.tfg.app.util.exceptions.Config;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayPersistDto;
import es.udc.rdopazo.tfg.service.api.route.dto.RoutePersistDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPersistDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel/routes")
public class AdminPanelRoutesController {

    @Autowired
    ClientRouteAdmin clientRouteAdmin;

    @Autowired
    ClientRouteDayAdmin clientRouteDayAdmin;

    @Autowired
    ClientStayAdmin clientStayAdmin;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanelRoutes(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        model.addAttribute("role", token.getRole());

        if (WebInputValidation.validateRole(Role.ADMIN, token.getRole())) {
            return "adminpanel/adminpanelroutes";
        } else {
            return "error";
        }

    }

    @RequestMapping(path = "/ajax/route", method = RequestMethod.GET)
    public String routesFrag(HttpServletRequest request, @RequestParam(name = "user") Optional<String> user,
            @RequestParam(name = "filterBy") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InstanceNotFoundException, InputValidationException {

        String userStr = WebInputValidation.validateOptionalNull(user);
        String filterStr = WebInputValidation.validateOptionalEmpty(filter);
        String valueStr = WebInputValidation.validateOptionalEmpty(value).replaceAll("\\+", " ");
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        Integer indexInt = 0;
        if (index.isPresent()) {
            indexInt = Integer.parseInt(index.get());
            model.addAttribute("index", Integer.parseInt(index.get()) + 1);
        } else {
            model.addAttribute("index", 1);
        }

        indexInt = indexInt * Config.PAGINATION;
        List<RoutePersistDto> routes = new ArrayList<>();

        routes = this.clientRouteAdmin.getService(token.getToken()).getAll(userStr, filterStr, valueStr,
                indexInt.toString(), Config.PAGINATION.toString());

        if (routes.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }

        model.addAttribute("userValue", userStr == "null" ? "" : userStr);
        model.addAttribute("filterField", filterStr);
        model.addAttribute("filterValue", valueStr);
        model.addAttribute("routes", routes);
        return "fragments/adminpanel/adminpanelroutesfrag :: route";
    }

    @RequestMapping(path = "/ajax/routeday", method = RequestMethod.GET)
    public String routesDayFrag(HttpServletRequest request, @RequestParam(name = "route") Optional<String> route,
            @RequestParam(name = "filterBy") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InstanceNotFoundException, InputValidationException {

        String routeStr = WebInputValidation.validateOptionalNull(route);
        String filterStr = WebInputValidation.validateOptionalEmpty(filter);
        String valueStr = WebInputValidation.validateOptionalEmpty(value).replaceAll("\\+", " ");
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        Integer indexInt = 0;
        if (index.isPresent()) {
            indexInt = Integer.parseInt(index.get());
            model.addAttribute("index", Integer.parseInt(index.get()) + 1);
        } else {
            model.addAttribute("index", 1);
        }

        indexInt = indexInt * Config.PAGINATION;
        List<RouteDayPersistDto> routeDay = new ArrayList<>();

        routeDay = this.clientRouteDayAdmin.getService(token.getToken()).getAll(routeStr, filterStr, valueStr,
                indexInt.toString(), Config.PAGINATION.toString());

        if (routeDay.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }
        model.addAttribute("routeValue", routeStr == "null" ? "" : routeStr);
        model.addAttribute("filterField", filterStr);
        model.addAttribute("filterValue", valueStr);
        model.addAttribute("routeDays", routeDay);
        return "fragments/adminpanel/adminpanelroutesfrag :: routeday";
    }

    @GetMapping("/ajax/stay")
    public String routesStaysFrag(HttpServletRequest request, @RequestParam(name = "route") Optional<String> route,
            @RequestParam(name = "day") Optional<String> day, @RequestParam(name = "filterBy") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InstanceNotFoundException, InputValidationException {

        String routeStr = WebInputValidation.validateOptionalNull(route);
        String dayStr = WebInputValidation.validateOptionalNull(day);
        String filterStr = WebInputValidation.validateOptionalEmpty(filter);
        String valueStr = WebInputValidation.validateOptionalEmpty(value).replaceAll("\\+", " ");
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        Integer indexInt = 0;
        if (index.isPresent()) {
            indexInt = Integer.parseInt(index.get());
            model.addAttribute("index", Integer.parseInt(index.get()) + 1);
        } else {
            model.addAttribute("index", 1);
        }

        indexInt = indexInt * Config.PAGINATION;
        List<StayPersistDto> stays = new ArrayList<>();
        stays = this.clientStayAdmin.getService(token.getToken()).getAll(routeStr, dayStr, filterStr, valueStr,
                indexInt.toString(), Config.PAGINATION.toString());

        if (stays.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }
        model.addAttribute("routeValue", routeStr == "null" ? "" : routeStr);
        model.addAttribute("dayValue", dayStr == "null" ? "" : dayStr);
        model.addAttribute("filterField", filterStr);
        model.addAttribute("filterValue", valueStr);
        model.addAttribute("stays", stays);
        return "fragments/adminpanel/adminpanelroutesfrag :: stay";
    }

    @DeleteMapping("/ajax/{entity}/{id}")
    public ResponseEntity delete(HttpServletRequest request, @PathVariable(name = "entity") String entity,
            @PathVariable(name = "id") String id, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        switch (entity.toUpperCase()) {
        case "ROUTE":
            try {
                this.clientRouteAdmin.getService(token.getToken()).delete(id);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        case "STAY":
            try {
                this.clientStayAdmin.getService(token.getToken()).delete(id);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/route/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity update(HttpServletRequest request, @PathVariable(name = "id") String id,
            @RequestBody RoutePersistDto entityDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            RoutePersistDto oldRoute = this.clientRouteAdmin.getService(token.getToken()).getById(id);
            this.clientRouteAdmin.getService(token.getToken()).update(id, entityDto);
            if (oldRoute.getNumDays() != entityDto.getNumDays()) {
                this.clientRouteDayAdmin.getService(token.getToken()).createNumDays(id, entityDto.getNumDays());
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/routeday/{id}/{idDay}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<?> updateRouteDay(HttpServletRequest request, @PathVariable(name = "id") String id,
            @PathVariable(name = "idDay") String idDay, @RequestBody RouteDayPersistDto entityDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientRouteDayAdmin.getService(token.getToken()).update(id, idDay, entityDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/stay/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<?> update(HttpServletRequest request, @PathVariable(name = "id") String id,
            @RequestBody StayPersistDto stayPersistDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientStayAdmin.getService(token.getToken()).update(id, stayPersistDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/route", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createRoute(HttpServletRequest request, @RequestBody RoutePersistDto routePersistDto,
            Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientRouteAdmin.getService(token.getToken()).create(routePersistDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/routeday/{id}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createRouteDay(HttpServletRequest request, @PathVariable(name = "id") String id,
            @RequestBody RouteDayPersistDto routeDayPersistDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientRouteDayAdmin.getService(token.getToken()).create(id, routeDayPersistDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/stay", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createStay(HttpServletRequest request, @RequestBody StayPersistDto stayPersistDto,
            Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientStayAdmin.getService(token.getToken()).create(stayPersistDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/ajax/object/{entity}/{id}")
    public ResponseEntity<?> getById(HttpServletRequest request, @PathVariable(name = "entity") String entity,
            @PathVariable(name = "id") String id, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        switch (entity.toUpperCase()) {
        case "ROUTE":
            try {
                return new ResponseEntity<RoutePersistDto>(
                        this.clientRouteAdmin.getService(token.getToken()).getById(id), null, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        case "STAY":
            try {
                return new ResponseEntity<StayPersistDto>(this.clientStayAdmin.getService(token.getToken()).getById(id),
                        null, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ajax/object/routeday/{idRoute}/{idDay}")
    public ResponseEntity<RouteDayPersistDto> getByIdRD(HttpServletRequest request,
            @PathVariable(name = "idRoute") String idRoute, @PathVariable(name = "idDay") String idDay, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        try {
            return new ResponseEntity<RouteDayPersistDto>(
                    this.clientRouteDayAdmin.getService(token.getToken()).getById(idRoute, idDay), null, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
    }
}
