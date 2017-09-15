package es.udc.rdopazo.tfg.app.model.core.foursquare.impl;

import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.foursquare.FoursquareService;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

@Service
public class FousquareServiceImpl implements FoursquareService {

    public CompactVenue[] obtenerLugaresCiudad(String nombre) {

        FoursquareApi foursquareApi = new FoursquareApi("QVCGTXJPDGQERVGOWYIJD4CDHAKDUHG3PEBSOQF2XA0KSMJX",
                "IH13FHC1J332RGX04DREJNKQU4TGTRRNGK2OUXTRK5XXA5SE", "http://localhost:8080/eTravel");
        // TODO Auto-generated method stub
        Result<VenuesSearchResult> result = null;
        try {
            result = foursquareApi.venuesSearch(nombre, null, 5, null, null, null, null, null);
        } catch (FoursquareApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CompactVenue[] v = result.getResult().getVenues();
        return v;
    }
}
