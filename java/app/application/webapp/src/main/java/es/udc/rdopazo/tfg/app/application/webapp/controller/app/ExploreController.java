package es.udc.rdopazo.tfg.app.application.webapp.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.udc.rdopazo.tfg.app.application.webapp.util.WebInputValidation;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/explore")
public class ExploreController {

    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        model.addAttribute("role", token.getRole());

        if (WebInputValidation.validateRole(Role.USER, token.getRole())) {
            return "app/explore";
        } else {
            return "error";
        }

    }
}
