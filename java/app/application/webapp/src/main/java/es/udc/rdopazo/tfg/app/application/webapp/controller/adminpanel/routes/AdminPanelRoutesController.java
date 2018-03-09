package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel.routes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientRouteAdminResource;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientUserResource;
import es.udc.rdopazo.tfg.app.util.exceptions.Config;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.route.dto.RoutePersistDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel/routes")
public class AdminPanelRoutesController {

    @Autowired
    ClientRouteAdminResource clientRouteAdmin;

    @Autowired
    ClientUserResource clientUser;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanelRoutes(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        model.addAttribute("role", token.getRole());
        System.out.println(token.getRole());

        return "adminpanel/adminpanelroutes";
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
        return "fragments/adminpanel/adminpanelfrag :: routes";
    }
}
