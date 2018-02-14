package es.udc.rdopazo.tfg.app.client.resteasy;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class HeaderFilter implements ClientRequestFilter {

    public void filter(ClientRequestContext requestContext) throws IOException {

        requestContext.getHeaders().add("Authorization",
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImV4cCI6MTUxODY0NzU3M30.3HSwKqCTdOevVw5w68j1cVSV_GHDRymWyx602GLw7GApphrkfeAbbnfqMPMywG4AzaalTqBgZYDn0dqtCTxInA");

    }

}
