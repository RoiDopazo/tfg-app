package es.udc.rdopazo.tfg.app.application.webapp.controller.adminpanel;

// @RestController
// @RequestMapping("/dto/admin/panel")
// @SessionAttributes({ "token" })
// public class AdminPanelRestController {
//
// @Autowired
// ClientRouteResource clientRoute;
//
// @Autowired
// ClientUserResource clientUser;
//
// @GetMapping("/ajax/route/{id}")
// public ResponseEntity<RouteDto> getById(HttpServletRequest request, @PathVariable(name = "id") String id,
// Model model) {
//
// TokenDto token = (TokenDto) request.getSession().getAttribute("token");
//
// try {
// return new ResponseEntity<RouteDto>(this.clientRoute.getService(token.getToken()).getById(id), null,
// HttpStatus.OK);
// } catch (InstanceNotFoundException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// return null;
// }
//
// }
