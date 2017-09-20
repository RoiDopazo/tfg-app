package es.udc.rdopazo.tfg.app.model.core.foursquare.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.foursquare.FoursquareService;
import es.udc.rdopazo.tfg.app.model.core.util.FoursquareClient;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.LikeGroup;
import fi.foyt.foursquare.api.entities.Photo;
import fi.foyt.foursquare.api.entities.PhotoGroup;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

@Service
public class FousquareServiceImpl implements FoursquareService {

    @Autowired
    FoursquareClient foursquareClient;

    public CompactVenue[] getPlacesByCity(String nombre, Integer limit, String idCategoria) {

        // TODO Auto-generated method stub
        Result<VenuesSearchResult> result = null;
        try {
            result = this.foursquareClient.getFoursquareApiClient().venuesSearch(nombre, null, limit, null, idCategoria,
                    null, null, null);
        } catch (FoursquareApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CompactVenue[] v = result.getResult().getVenues();
        return v;
    }

    public String getPhoto(String lugarId) {

        Result<PhotoGroup> result = null;
        try {
            result = this.foursquareClient.getFoursquareApiClient().venuesPhotos(lugarId, null, 1, null);
        } catch (FoursquareApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (result.getResult().getItems().length != 0) {
            Photo photo = result.getResult().getItems()[0];
            return (photo.getPrefix() + "300x200" + photo.getSuffix());
        } else {
            return "none";
        }
    }

    public Long getNumLikes(String lugar) {
        Result<LikeGroup> result = null;
        try {
            result = this.foursquareClient.getFoursquareApiClient().venuesLikes(lugar);
        } catch (FoursquareApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result.getResult().getCount();
    }

}