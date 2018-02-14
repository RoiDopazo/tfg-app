package es.udc.rdopazo.tfg.app.application.webapp.controller.login.remember;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/step1")
@SessionAttributes({ "name", "pass" })
public class Step1 {

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {

        String nombre = request.getSession().getAttribute("name").toString();
        String pass = request.getSession().getAttribute("pass").toString();

        System.out.println(pass);
        return "step1";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login_receive(@ModelAttribute("userDto") UserDto userDto, Model model) {
        return "login2";
    }
}
