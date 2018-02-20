package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientRouteResource;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel")
public class AdminPanelController {

    @Autowired
    ClientRouteResource clientRoute;

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanel(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        model.addAttribute("role", token.getRole());
        System.out.println(token.getRole());

        return "adminpanel";
    }

    @GetMapping("/frag1")
    public String frag1(HttpServletRequest request, Model model) {
        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        RouteDto r = new RouteDto();
        r.setName("fail");
        // try {
        // r = this.clientRoute.getService(token.getToken()).getById("5");
        // } catch (InstanceNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        model.addAttribute("content", r.getName());
        return "fragments/frag1 :: content";
    }
}
