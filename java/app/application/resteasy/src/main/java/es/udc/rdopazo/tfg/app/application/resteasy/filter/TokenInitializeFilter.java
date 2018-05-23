package es.udc.rdopazo.tfg.app.application.resteasy.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import es.udc.rdopazo.tfg.app.application.resteasy.spring.SpringApplicationContext;
import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class TokenInitializeFilter<U extends Usuario> implements ContainerResponseFilter {

    @SuppressWarnings("unchecked")
    UsuarioService<U> usuarioService = (UsuarioService<U>) SpringApplicationContext.getBean(UsuarioService.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        // String strToken = null;
        // if (responseContext.getStatus() == HttpStatus.SC_OK) {
        //
        // String usuarioHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        // U usuario = this.usuarioService.getByField("nombre", usuarioHeader).get(0);
        //
        // Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        // strToken = usuario.getNombre() + "|" + timestamp.getTime();
        // Object token = null;
        // try {
        // token = TokenEncription.encrypt(strToken);
        // } catch (ClassNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // responseContext.getHeaders().add("X-Authentication", token.toString());
        // }
    }

}
