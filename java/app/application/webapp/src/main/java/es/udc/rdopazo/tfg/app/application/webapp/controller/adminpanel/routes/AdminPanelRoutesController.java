package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel.routes;

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

import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientRouteAdmin;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientRouteDayAdmin;
import es.udc.rdopazo.tfg.app.util.exceptions.Config;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayPersistDto;
import es.udc.rdopazo.tfg.service.api.route.dto.RoutePersistDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel/routes")
public class AdminPanelRoutesController {

    @Autowired
    ClientRouteAdmin clientRouteAdmin;

    @Autowired
    ClientRouteDayAdmin clientRouteDayAdmin;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanelRoutes(HttpServletRequest request, Model model) {

        return "adminpanel/adminpanelroutes";
    }

    @RequestMapping(path = "/ajax/route", method = RequestMethod.GET)
    public String routesFrag(HttpServletRequest request, @RequestParam(name = "filterBy") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InstanceNotFoundException {

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

        if ((filter.isPresent()) && (value.isPresent())) {
            if ((filter.get().length() > 0) && (value.get().length() > 0)) {
                try {
                    routes = this.clientRouteAdmin.getService(token.getToken()).getAll(filter.get(), value.get(),
                            indexInt.toString(), Config.PAGINATION.toString());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                model.addAttribute("filterField", filter.get());
                model.addAttribute("filterValue", value.get());
            } else {
                try {
                    routes = this.clientRouteAdmin.getService(token.getToken()).getAll(null, null, indexInt.toString(),
                            Config.PAGINATION.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                routes = this.clientRouteAdmin.getService(token.getToken()).getAll(null, null, indexInt.toString(),
                        Config.PAGINATION.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (routes.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }
        model.addAttribute("routes", routes);
        return "fragments/adminpanel/adminpanelroutesfrag :: route";
    }

    @RequestMapping(path = "/ajax/routeday", method = RequestMethod.GET)
    public String routesDayFrag(HttpServletRequest request, @RequestParam(name = "filterBy") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InstanceNotFoundException {

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

        if ((filter.isPresent()) && (value.isPresent())) {
            if ((filter.get().length() > 0) && (value.get().length() > 0)) {
                try {
                    routeDay = this.clientRouteDayAdmin.getService(token.getToken()).getAll(filter.get(), value.get(),
                            indexInt.toString(), Config.PAGINATION.toString());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                model.addAttribute("filterField", filter.get());
                model.addAttribute("filterValue", value.get());
            } else {
                try {
                    routeDay = this.clientRouteDayAdmin.getService(token.getToken()).getAll(null, null,
                            indexInt.toString(), Config.PAGINATION.toString());
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        } else {
            try {
                routeDay = this.clientRouteDayAdmin.getService(token.getToken()).getAll(null, null, indexInt.toString(),
                        Config.PAGINATION.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (routeDay.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }
        model.addAttribute("routeDays", routeDay);
        return "fragments/adminpanel/adminpanelroutesfrag :: routeday";
    }

    @GetMapping("/ajax/stay")
    public String routesStaysFrag(HttpServletRequest request, @RequestParam(name = "filterBy") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InstanceNotFoundException {
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

        if ((filter.isPresent()) && (value.isPresent())) {
            if ((filter.get().length() > 0) && (value.get().length() > 0)) {
                try {
                    routes = this.clientRouteAdmin.getService(token.getToken()).getAll(filter.get(), value.get(),
                            indexInt.toString(), Config.PAGINATION.toString());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                model.addAttribute("filterField", filter.get());
                model.addAttribute("filterValue", value.get());
            } else {
                try {
                    routes = this.clientRouteAdmin.getService(token.getToken()).getAll(null, null, indexInt.toString(),
                            Config.PAGINATION.toString());
                } catch (Exception e) {

                }
            }
        } else {
            try {
                routes = this.clientRouteAdmin.getService(token.getToken()).getAll(null, null, indexInt.toString(),
                        Config.PAGINATION.toString());
            } catch (Exception e) {
            }
        }

        if (routes.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }
        model.addAttribute("content", routes);
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
        case "ROUTEDAY":
            try {
                this.clientRouteDayAdmin.getService(token.getToken()).delete(id);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        case "STAY":
            break;
        }

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/{entity}/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity update(HttpServletRequest request, @PathVariable(name = "entity") String entity,
            @PathVariable(name = "id") String id, @RequestBody EntityDto entityDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        switch (entity.toUpperCase()) {
        case "ROUTE":
            try {
                this.clientRouteAdmin.getService(token.getToken()).update(id, (RoutePersistDto) entityDto);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        case "STAY":
            break;
        }

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/routeDay/{id}/{idDay}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity updateRouteDay(HttpServletRequest request, @PathVariable(name = "id") String id,
            @PathVariable(name = "idDay") String idDay, @RequestBody EntityDto entityDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientRouteDayAdmin.getService(token.getToken()).update(id, idDay, (RouteDayPersistDto) entityDto);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/ajax/object/{entity}/{id}")
    public ResponseEntity<RoutePersistDto> getById(HttpServletRequest request,
            @PathVariable(name = "entity") String entity, @PathVariable(name = "id") String id, Model model) {

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

        }
        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ajax/object/routeday/{idRoute}/{idDay}")
    public ResponseEntity<RouteDayPersistDto> getByIdRD(HttpServletRequest request, @PathVariable(name = "idRoute") String idRoute,
            @PathVariable(name = "idDay") String idDay, Model model) {

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
