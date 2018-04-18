package es.udc.rdopazo.tfg.app.application.webapp.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import es.udc.rdopazo.tfg.app.application.webapp.util.WebInputValidation;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientUser;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@Controller
@RequestMapping("/login")
@SessionAttributes({ "token" })
public class LoginController {

    @Autowired
    ClientUser clientUser;

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public View do_login(HttpServletRequest request, @ModelAttribute("userDto") UsuarioDto userDto, Model model,
            RedirectAttributes attributes) {

        TokenDto token = null;
        String redirectView = "";
        try {
            token = this.clientUser.getService(null).authenticate(userDto);

        } catch (Exception e) {
            redirectView = "/login";
            attributes.addFlashAttribute("error");
            RedirectView redirect = new RedirectView(redirectView);
            return redirect;
        }

        model.addAttribute("token", token);

        if (WebInputValidation.validateRole(Role.USER, token.getRole())) {
            redirectView = "/index";
        } else if ((WebInputValidation.validateRole(Role.ADMIN, token.getRole()))
                || (WebInputValidation.validateRole(Role.MODERATOR, token.getRole()))) {
            redirectView = "/admin/panel";
        }

        RedirectView redirect = new RedirectView(redirectView);
        attributes.addFlashAttribute("err", "rererere");
        redirect.setExposeModelAttributes(false);
        return redirect;
    }
}
