package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.udc.rdopazo.tfg.app.application.webapp.util.WebInputValidation;
import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/admin/panel")
public class AdminPanelController {

    @RequestMapping(method = RequestMethod.GET)
    public String adminPanel(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        model.addAttribute("role", token.getRole());

        if (WebInputValidation.validateRole(Role.ADMIN, token.getRole())) {
            return "adminpanel/adminpanel";
        } else if (WebInputValidation.validateRole(Role.MODERATOR, token.getRole())) {
            return "adminpanel/adminpanelmod";
        } else {
            return "error";
        }

    }

}
