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

}
