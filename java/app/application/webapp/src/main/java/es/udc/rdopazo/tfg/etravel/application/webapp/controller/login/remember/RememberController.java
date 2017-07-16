package es.udc.rdopazo.tfg.etravel.application.webapp.controller.login.remember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class RememberController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {

        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login_receive(@ModelAttribute("userDto") UserDto userDto, HttpServletResponse response) {
        return "login2";
    }
}
