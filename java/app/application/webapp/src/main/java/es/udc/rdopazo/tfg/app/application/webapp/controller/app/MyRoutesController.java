package es.udc.rdopazo.tfg.app.application.webapp.controller.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.udc.rdopazo.tfg.app.application.webapp.util.WebInputValidation;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientRoute;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@SessionAttributes({ "token" })
@Controller
@RequestMapping("/myroutes")
public class MyRoutesController {

    @Autowired
    private ClientRoute clientRoute;

    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpServletRequest request, Model model) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());

        if (WebInputValidation.validateRole(Role.USER, token.getRole())) {

            return "app/myroutes";
        } else {
            return "error";
        }

    }

    @RequestMapping(method = RequestMethod.GET, path = "/ajax/getownroutes")
    public String getOwnRoutes(HttpServletRequest request, @RequestParam(name = "filter") Optional<String> filter,
            @RequestParam(name = "value") Optional<String> value, @RequestParam(name = "index") Optional<String> index,
            Model model) throws InputValidationException {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        model.addAttribute("name", token.getName());
        String filterStr = WebInputValidation.validateOptionalEmpty(filter);
        String valueStr = WebInputValidation.validateOptionalEmpty(value).replaceAll("\\+", " ");

        List<RouteDto> routes = new ArrayList<>();
        routes = this.clientRoute.getService(token.getToken()).getOwnRoutes(filterStr, valueStr, null, null);

        model.addAttribute("myroutes", routes);
        if (WebInputValidation.validateRole(Role.USER, token.getRole())) {
            return "fragments/app/myroutesfrag :: myroutes";
        } else {
            return "error";
        }

    }

}