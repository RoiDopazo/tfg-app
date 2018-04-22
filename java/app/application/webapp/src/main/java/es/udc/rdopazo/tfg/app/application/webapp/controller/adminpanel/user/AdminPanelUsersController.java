package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel.user;

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
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientUsuarioAdmin;
import es.udc.rdopazo.tfg.app.util.exceptions.Config;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioPersistDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel/users")
public class AdminPanelUsersController {

    @Autowired
    ClientUsuarioAdmin clientUsuarioAdmin;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanelRoutes(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        model.addAttribute("role", token.getRole());

        if (WebInputValidation.validateRole(Role.ADMIN, token.getRole())) {
            return "adminpanel/adminpanelusers";
        } else {
            return "error";
        }
    }

    @RequestMapping(path = "/ajax/user", method = RequestMethod.GET)
    public String usersFrag(HttpServletRequest request, @RequestParam(name = "filterBy") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InstanceNotFoundException, InputValidationException {

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
        List<UsuarioPersistDto> users = new ArrayList<>();

        users = this.clientUsuarioAdmin.getService(token.getToken()).getAll(filterStr, valueStr, indexInt.toString(),
                Config.PAGINATION.toString());

        if (users.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }

        model.addAttribute("filterField", filterStr);
        model.addAttribute("filterValue", valueStr);
        model.addAttribute("users", users);
        return "fragments/adminpanel/adminpanelusersfrag :: user";
    }

    @DeleteMapping("/ajax/user/{id}")
    public ResponseEntity delete(HttpServletRequest request, @PathVariable(name = "id") String id, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientUsuarioAdmin.getService(token.getToken()).delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/user/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity update(HttpServletRequest request, @PathVariable(name = "id") String id,
            @RequestBody UsuarioPersistDto entityDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientUsuarioAdmin.getService(token.getToken()).update(id, entityDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createUser(HttpServletRequest request, @RequestBody UsuarioPersistDto entityDto,
            Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientUsuarioAdmin.getService(token.getToken()).create(entityDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @GetMapping("/ajax/object/user/{id}")
    public ResponseEntity<?> getById(HttpServletRequest request, @PathVariable(name = "id") String id, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        try {
            return new ResponseEntity<UsuarioPersistDto>(
                    this.clientUsuarioAdmin.getService(token.getToken()).getById(id), null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
    }
}
