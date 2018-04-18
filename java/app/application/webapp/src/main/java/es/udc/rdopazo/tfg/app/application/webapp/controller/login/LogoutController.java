package es.udc.rdopazo.tfg.app.application.webapp.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {

        request.getSession().removeAttribute("token");
        model.addAttribute("logout");
        return "login";
    }
}
