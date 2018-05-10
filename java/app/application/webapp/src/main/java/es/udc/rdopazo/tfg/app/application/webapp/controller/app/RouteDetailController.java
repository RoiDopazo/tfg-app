package es.udc.rdopazo.tfg.app.application.webapp.controller.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientRoute;
import es.udc.rdopazo.tfg.app.client.resteasy.resource.ClientRouteDay;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayDto;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@Controller
@RequestMapping("/route/info")
@SessionAttributes({ "token" })
public class RouteDetailController {

    @Autowired
    private ClientRoute clientRoute;

    @Autowired
    private ClientRouteDay clientRouteDay;

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public String get(HttpServletRequest request, @PathVariable(name = "id") String id, Model model,
            RedirectAttributes attributes) {

        return "app/routedetail";
    }

    @RequestMapping(path = "/ajax/{id}/day/{day}", method = RequestMethod.GET)
    public String getday(HttpServletRequest request, @PathVariable(name = "id") String id,
            @PathVariable(name = "day") String day, Model model, RedirectAttributes attributes) {

        TokenDto token = (TokenDto) request.getSession().getAttribute("token");
        RouteDayDto routeDay = null;
        RouteDto route = null;
        try {
            route = this.clientRoute.getService(token.getToken()).getById(id);
            routeDay = this.clientRouteDay.getService(token.getToken()).getById(id, day);
        } catch (Exception e) {
            return "error";
        }

        model.addAttribute("startDate", route.getStartDate());
        model.addAttribute("numDays", route.getDays().size());
        model.addAttribute("day", routeDay);
        model.addAttribute("stays", routeDay.getStays());
        model.addAttribute("centerLat",
                routeDay.getStays().isEmpty() ? route.getLat()
                        : routeDay.getStays().get(0).getEventPlace() == null
                                ? routeDay.getStays().get(0).getPlace().getLat()
                                : routeDay.getStays().get(0).getEventPlace().getLat());
        model.addAttribute("centerLng",
                routeDay.getStays().isEmpty() ? route.getLng()
                        : routeDay.getStays().get(0).getEventPlace() == null
                                ? routeDay.getStays().get(0).getPlace().getLng()
                                : routeDay.getStays().get(0).getEventPlace().getLng());
        return "fragments/app/routedetailfrag :: route";
    }
}
