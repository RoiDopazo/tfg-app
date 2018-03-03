package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel;

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

import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientRouteResource;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientUserResource;
import es.udc.rdopazo.tfg.app.util.exceptions.Config;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel")
public class AdminPanelController {

    @Autowired
    ClientRouteResource clientRoute;

    @Autowired
    ClientUserResource clientUser;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanel(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        model.addAttribute("role", token.getRole());
        System.out.println(token.getRole());

        return "adminpanel";
    }

    @GetMapping("/ajax/user")
    public String usersFrag(HttpServletRequest request, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        List<UsuarioDto> users = this.clientUser.getService(token.getToken()).getAll();
        model.addAttribute("content", users);
        model.addAttribute("index", 20);
        return "fragments/adminpanel/adminpanelfrag :: users";
    }

    @GetMapping("/ajax/route")
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
        List<RouteDto> routes = new ArrayList<>();

        if ((filter.isPresent()) && (value.isPresent())) {
            if ((filter.get().length() > 0) && (value.get().length() > 0)) {
                routes = this.clientRoute.getService(token.getToken()).getAll(filter.get(), value.get(),
                        indexInt.toString(), Config.PAGINATION.toString());
                model.addAttribute("filterField", filter.get());
                model.addAttribute("filterValue", value.get());
            } else {
                routes = this.clientRoute.getService(token.getToken()).getAll(null, null, indexInt.toString(),
                        Config.PAGINATION.toString());
            }
        } else {
            routes = this.clientRoute.getService(token.getToken()).getAll(null, null, indexInt.toString(),
                    Config.PAGINATION.toString());
        }

        if (routes.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }
        model.addAttribute("content", routes);
        return "fragments/adminpanel/adminpanelfrag :: routes";
    }

    @GetMapping("/ajax/object/route/{id}")
    public ResponseEntity<RouteDto> getById(HttpServletRequest request, @PathVariable(name = "id") String id,
            Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            return new ResponseEntity<RouteDto>(this.clientRoute.getService(token.getToken()).getById(id), null,
                    HttpStatus.OK);
        } catch (InstanceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/ajax/{entity}/{id}")
    public ResponseEntity delete(HttpServletRequest request, @PathVariable(name = "entity") String entity,
            @PathVariable(name = "id") String id, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        switch (entity.toUpperCase()) {
        case "ROUTE":
            try {
                this.clientRoute.getService(token.getToken()).delete(id);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/{entity}/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity update(HttpServletRequest request, @PathVariable(name = "entity") String entity,
            @PathVariable(name = "id") String id, @RequestBody RouteDto routeDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        switch (entity.toUpperCase()) {
        case "ROUTE":
            try {
                this.clientRoute.getService(token.getToken()).update(id, routeDto);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

}