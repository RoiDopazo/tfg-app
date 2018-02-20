package es.udc.rdopazo.tfg.app.application.webapp.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientUserResource;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@Controller
@RequestMapping("/login222")
@SessionAttributes({ "name", "pass" })
public class RememberController {

    @Autowired
    ClientUserResource clientUser;

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {

        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login_receive(@ModelAttribute("userDto") UsuarioDto userDto, Model model) {
        TokenDto token = this.clientUser.getService(null).authenticate(userDto);
        model.addAttribute("name", token.getToken());
        model.addAttribute("pass", userDto.getPassword());
        return "login2";
    }
}
