package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel.foursquare;

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
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientCategoryAdmin;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientPlaceAdmin;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientSubCategoryAdmin;
import es.udc.rdopazo.tfg.app.util.exceptions.Config;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.category.dto.CategoryPersistDto;
import es.udc.rdopazo.tfg.service.api.place.dto.PlacePersistDto;
import es.udc.rdopazo.tfg.service.api.subcategory.dto.SubCategoryPersistDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel/foursquare")
public class AdminPanlesFoursquareDataController {

    @Autowired
    private ClientPlaceAdmin clientPlaceAdmin;

    @Autowired
    private ClientCategoryAdmin clientCategoryAdmin;

    @Autowired
    private ClientSubCategoryAdmin clientSubCategoryAdmin;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanelFoursquare(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        model.addAttribute("role", token.getRole());

        if (WebInputValidation.validateRole(Role.ADMIN, token.getRole())) {
            return "adminpanel/adminpanelfoursquare";
        } else {
            return "error";
        }
    }

    @RequestMapping(path = "/ajax/place", method = RequestMethod.GET)
    public String placeFrag(HttpServletRequest request, @RequestParam(name = "filterBy") Optional<String> filter,
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
        List<PlacePersistDto> places = new ArrayList<>();

        places = this.clientPlaceAdmin.getService(token.getToken()).getAll(filterStr, valueStr, indexInt.toString(),
                Config.PAGINATION.toString());

        if (places.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }

        model.addAttribute("filterField", filterStr);
        model.addAttribute("filterValue", valueStr);
        model.addAttribute("places", places);
        return "fragments/adminpanel/adminpanelfoursquarefrag :: place";
    }

    @RequestMapping(path = "/ajax/category", method = RequestMethod.GET)
    public String categoryFrag(HttpServletRequest request, @RequestParam(name = "filterBy") Optional<String> filter,
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
        List<CategoryPersistDto> categories = new ArrayList<>();

        categories = this.clientCategoryAdmin.getService(token.getToken()).getAll(filterStr, valueStr,
                indexInt.toString(), Config.PAGINATION.toString());

        if (categories.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }

        model.addAttribute("filterField", filterStr);
        model.addAttribute("filterValue", valueStr);
        model.addAttribute("categories", categories);
        return "fragments/adminpanel/adminpanelfoursquarefrag :: category";
    }

    @RequestMapping(path = "/ajax/subcategory", method = RequestMethod.GET)
    public String subcategoryFrag(HttpServletRequest request,
            @RequestParam(name = "category") Optional<String> category,
            @RequestParam(name = "filterBy") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InstanceNotFoundException, InputValidationException {

        String categoryStr = WebInputValidation.validateOptionalNull(category);
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
        List<SubCategoryPersistDto> subcategories = new ArrayList<>();

        subcategories = this.clientSubCategoryAdmin.getService(token.getToken()).getAll(categoryStr, filterStr,
                valueStr, indexInt.toString(), Config.PAGINATION.toString());

        if (subcategories.size() < Config.PAGINATION) {
            model.addAttribute("isLastPage", true);
        }

        model.addAttribute("categoryValue", categoryStr == "null" ? "" : categoryStr);
        model.addAttribute("filterField", filterStr);
        model.addAttribute("filterValue", valueStr);
        model.addAttribute("subcategories", subcategories);
        return "fragments/adminpanel/adminpanelfoursquarefrag :: subcategory";
    }

    @RequestMapping(path = "/ajax/place/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<?> updatePlace(HttpServletRequest request, @PathVariable(name = "id") String id,
            @RequestBody PlacePersistDto placePersistDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientPlaceAdmin.getService(token.getToken()).update(id, placePersistDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/category/load", method = RequestMethod.POST)
    public ResponseEntity<?> loadCategories(HttpServletRequest request, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientCategoryAdmin.getService(token.getToken()).load();
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/category/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<?> updateCategory(HttpServletRequest request, @PathVariable(name = "id") String id,
            @RequestBody CategoryPersistDto categoryPersistDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientCategoryAdmin.getService(token.getToken()).update(id, categoryPersistDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @RequestMapping(path = "/ajax/subcategory/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<?> updateSubCategory(HttpServletRequest request, @PathVariable(name = "id") String id,
            @RequestBody SubCategoryPersistDto subcategoryPersistDto, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        try {
            this.clientSubCategoryAdmin.getService(token.getToken()).update(id, subcategoryPersistDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    @DeleteMapping("/ajax/{entity}/{id}")
    public ResponseEntity delete(HttpServletRequest request, @PathVariable(name = "id") String id,
            @PathVariable(name = "entity") String entity, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");

        switch (entity.toUpperCase()) {
        case "PLACE":
            try {
                this.clientPlaceAdmin.getService(token.getToken()).delete(id);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
            break;
        case "CATEGORY":
            try {
                this.clientCategoryAdmin.getService(token.getToken()).delete(id);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
            break;
        case "SUBCATEGORY":
            try {
                this.clientSubCategoryAdmin.getService(token.getToken()).delete(id);
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
        case "PLACE":
            try {
                return new ResponseEntity<PlacePersistDto>(
                        this.clientPlaceAdmin.getService(token.getToken()).getById(id), null, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        case "CATEGORY":
            try {
                return new ResponseEntity<CategoryPersistDto>(
                        this.clientCategoryAdmin.getService(token.getToken()).getById(id), null, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        case "SUBCATEGORY":
            try {
                return new ResponseEntity<SubCategoryPersistDto>(
                        this.clientSubCategoryAdmin.getService(token.getToken()).getById(id), null, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }
}
