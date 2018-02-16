package es.udc.rdopazo.tfg.app.application.webapp.controller.login.remember;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/login")
@SessionAttributes({ "name", "pass" })
public class RememberController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {

        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login_receive(@ModelAttribute("userDto") UserDto userDto, Model model) {
        model.addAttribute("name", "asdvxcv.xcv-xcvxcv");
        model.addAttribute("pass", userDto.getPassword());
        return "login2";
    }
}
