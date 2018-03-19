package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientUser;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.admin.ClientRouteAdmin;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel")
public class AdminPanelController {

    @Autowired
    ClientRouteAdmin clientRouteAdmin;

    @Autowired
    ClientUser clientUser;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanel(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        model.addAttribute("role", token.getRole());
        System.out.println(token.getRole());

        return "adminpanel/adminpanel";
    }
    //
    // @GetMapping("/ajax/user")
    // public String usersFrag(HttpServletRequest request, Model model) {
    // TokenDto token = (TokenDto) request.getSession().getAttribute("token");
    // List<UsuarioDto> users = this.clientUser.getService(token.getToken()).getAll();
    // model.addAttribute("content", users);
    // model.addAttribute("index", 20);
    // return "fragments/adminpanel/adminpanelfrag :: users";
    // }
    //
    // @GetMapping("/ajax/route")
    // public String routesFrag(HttpServletRequest request, @RequestParam(name = "filterBy") Optional<String> filter,
    // @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
    // Model model) throws InstanceNotFoundException {
    // TokenDto token = (TokenDto) request.getSession().getAttribute("token");
    // Integer indexInt = 0;
    // if (index.isPresent()) {
    // indexInt = Integer.parseInt(index.get());
    // model.addAttribute("index", Integer.parseInt(index.get()) + 1);
    // } else {
    // model.addAttribute("index", 1);
    // }
    //
    // indexInt = indexInt * Config.PAGINATION;
    // List<RoutePersistDto> routes = new ArrayList<>();
    //
    // if ((filter.isPresent()) && (value.isPresent())) {
    // if ((filter.get().length() > 0) && (value.get().length() > 0)) {
    // try {
    // routes = this.clientRouteAdmin.getService(token.getToken()).getAll(filter.get(), value.get(),
    // indexInt.toString(), Config.PAGINATION.toString());
    // } catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // model.addAttribute("filterField", filter.get());
    // model.addAttribute("filterValue", value.get());
    // } else {
    // try {
    // routes = this.clientRouteAdmin.getService(token.getToken()).getAll(null, null, indexInt.toString(),
    // Config.PAGINATION.toString());
    // } catch (Exception e) {
    //
    // }
    // }
    // } else {
    // try {
    // routes = this.clientRouteAdmin.getService(token.getToken()).getAll("null", "null", indexInt.toString(),
    // Config.PAGINATION.toString());
    // } catch (Exception e) {
    // }
    // }
    //
    // if (routes.size() < Config.PAGINATION) {
    // model.addAttribute("isLastPage", true);
    // }
    // model.addAttribute("content", routes);
    // return "fragments/adminpanel/adminpanelfrag :: routes";
    // }
    //
    // @GetMapping("/ajax/object/route/{id}")
    // public ResponseEntity<RoutePersistDto> getById(HttpServletRequest request, @PathVariable(name = "id") String id,
    // Model model) {
    //
    // TokenDto token = (TokenDto) request.getSession().getAttribute("token");
    //
    // try {
    // return new ResponseEntity<RoutePersistDto>(this.clientRouteAdmin.getService(token.getToken()).getById(id),
    // null, HttpStatus.OK);
    // } catch (InstanceNotFoundException | InputValidationException e) {
    // return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    // }
    // }
    //
    // @DeleteMapping("/ajax/{entity}/{id}")
    // public ResponseEntity delete(HttpServletRequest request, @PathVariable(name = "entity") String entity,
    // @PathVariable(name = "id") String id, Model model) {
    // TokenDto token = (TokenDto) request.getSession().getAttribute("token");
    //
    // switch (entity.toUpperCase()) {
    // case "ROUTE":
    // try {
    // this.clientRouteAdmin.getService(token.getToken()).delete(id);
    // } catch (Exception e) {
    // return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    // }
    // }
    //
    // return new ResponseEntity<>(null, null, HttpStatus.OK);
    // }
    //
    // @RequestMapping(path = "/ajax/{entity}/{id}", method = RequestMethod.PUT, consumes = "application/json")
    // public ResponseEntity update(HttpServletRequest request, @PathVariable(name = "entity") String entity,
    // @PathVariable(name = "id") String id, @RequestBody RoutePersistDto routeDto, Model model) {
    // TokenDto token = (TokenDto) request.getSession().getAttribute("token");
    //
    // switch (entity.toUpperCase()) {
    // case "ROUTE":
    // try {
    // this.clientRouteAdmin.getService(token.getToken()).update(id, routeDto);
    // } catch (Exception e) {
    // return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    // }
    // }
    //
    // return new ResponseEntity<>(null, null, HttpStatus.OK);
    // }

}
