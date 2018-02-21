package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientRouteResource;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientUserResource;
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

    @GetMapping("/users")
    public String usersFrag(HttpServletRequest request, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        List<UsuarioDto> users = this.clientUser.getService(token.getToken()).getAll();
        model.addAttribute("content", users);
        return "fragments/adminpanel/adminpanelfrag :: users";
    }

    @GetMapping("/routes")
    public String routesFrag(HttpServletRequest request, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        List<RouteDto> routes = this.clientRoute.getService(token.getToken()).getAll("0", "10");
        model.addAttribute("content", routes);
        return "fragments/adminpanel/adminpanelfrag :: routes";
    }
}
