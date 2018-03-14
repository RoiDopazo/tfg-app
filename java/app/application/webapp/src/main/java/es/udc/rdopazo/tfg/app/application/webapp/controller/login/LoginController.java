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

import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientUser;
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
    public View do_login(@ModelAttribute("userDto") UsuarioDto userDto, Model model, RedirectAttributes attributes) {
        TokenDto token = this.clientUser.getService(null).authenticate(userDto);
        model.addAttribute("token", token);

        RedirectView redirect = new RedirectView("/admin/panel");
        attributes.addFlashAttribute("err", "rererere");
        redirect.setExposeModelAttributes(false);
        return redirect;
    }
}
