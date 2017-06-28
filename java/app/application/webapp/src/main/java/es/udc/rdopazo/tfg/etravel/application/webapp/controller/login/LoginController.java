package es.udc.rdopazo.tfg.etravel.application.webapp.controller.login;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/index")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(@RequestParam(value = "name", required = false) String name, Model model) {

        model.addAttribute("name", name);
        ArrayList<Myobject> lista = new ArrayList<Myobject>();
        Myobject o1 = new Myobject();
        o1.setLat(43.35708819398717);
        o1.setLng(-8.404542285629004);

        Myobject o2 = new Myobject();
        o2.setLat(43.3677179256995);
        o2.setLng(-8.407947151333659);

        lista.add(o1);
        lista.add(o2);
        model.addAttribute("lista", lista);
        return "index";
    }
}
