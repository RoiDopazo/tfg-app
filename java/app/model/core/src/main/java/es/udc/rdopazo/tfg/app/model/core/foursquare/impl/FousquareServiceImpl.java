package es.udc.rdopazo.tfg.app.model.core.foursquare.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.core.foursquare.FoursquareService;
import es.udc.rdopazo.tfg.app.model.core.util.FoursquareClient;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.category.JpaCategory;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategory.JpaSubCategory;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.LikeGroup;
import fi.foyt.foursquare.api.entities.Photo;
import fi.foyt.foursquare.api.entities.PhotoGroup;
import fi.foyt.foursquare.api.entities.Recommendation;
import fi.foyt.foursquare.api.entities.Recommended;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

@Service
public class FousquareServiceImpl<C extends Category, S extends Stay<?, ?, ?>> implements FoursquareService<C> {

    @Autowired
    FoursquareClient foursquareClient;

    @Autowired
    CategoryService<C> categoryService;

    public Recommendation[] recommendedPlaces(String lat, String lng, Integer radius, String section, String query,
            Integer limit, Integer sortByDistance, String price) {

        String ll = lat + "," + lng;
        Result<Recommended> result = null;
        try {
            result = this.foursquareClient.getFoursquareApiClient().venuesExplore(ll, null, null, null, null, radius,
                    section, query, limit, null, sortByDistance, price);
        } catch (FoursquareApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Recommendation[] recomendationList = result.getResult().getGroups()[0].getItems();
        return recomendationList;
    }

    public Recommendation[] recommendedPlaces(String near, Integer radius, String section, String query, Integer limit,
            Integer sortByDistance, String price) {

        Result<Recommended> result = null;
        try {
            result = this.foursquareClient.getFoursquareApiClient().venuesExplore(null, near, null, null, null, radius,
                    section, query, limit, null, sortByDistance, price);
        } catch (FoursquareApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Recommendation[] recomendationList = result.getResult().getGroups()[0].getItems();
        return recomendationList;
    }

    public CompactVenue[] searchPlaces(String lat, String lng, String intent, Integer radius, String query,
            Integer limit, String categoryId) {

        // TODO Auto-generated method stub
        Result<VenuesSearchResult> result = null;
        String ll = lat + "," + lng;
        try {
            result = this.foursquareClient.getFoursquareApiClient().venuesSearch(ll, null, intent, radius, query, limit,
                    categoryId);
        } catch (FoursquareApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CompactVenue[] v = result.getResult().getVenues();
        return v;
    }

    public CompactVenue[] searchPlaces(String near, String intent, Integer radius, String query, Integer limit,
            String categoryId) {

        // TODO Auto-generated method stub
        Result<VenuesSearchResult> result = null;

        try {
            result = this.foursquareClient.getFoursquareApiClient().venuesSearch(null, near, intent, radius, query,
                    limit, categoryId);
        } catch (FoursquareApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CompactVenue[] v = result.getResult().getVenues();
        return v;
    }

    // public CompactVenue[] getPlacesByCoord(String nombre, Integer limit, String idCategoria) {
    //
    // // TODO Auto-generated method stub
    // Result<VenuesSearchResult> result = null;
    //
    // try {
    // result = this.foursquareClient.getFoursquareApiClient().venuesSearch(nombre, null, limit, null, idCategoria,
    // null, null, null);
    // } catch (FoursquareApiException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // CompactVenue[] v = result.getResult().getVenues();
    // return v;
    // }

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

    public CompactVenue[] getPlacesByCity(String nombre, Integer limit, String idCategoria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<C> getFoursquareCategories() {

        Result<fi.foyt.foursquare.api.entities.Category[]> categories = null;
        try {
            categories = this.foursquareClient.getFoursquareApiClient().venuesCategories();
        } catch (FoursquareApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<JpaSubCategory> subList = null;
        for (fi.foyt.foursquare.api.entities.Category c : categories.getResult()) {
            JpaCategory cat = new JpaCategory();
            cat.setId_foursquare(c.getId());
            cat.setName(c.getName());
            cat.setPluralName(c.getPluralName());
            cat.setIconPrefix(c.getIcon().getPrefix());
            cat.setIconSuffix(c.getIcon().getSuffix());
            subList = new ArrayList<JpaSubCategory>();
            for (fi.foyt.foursquare.api.entities.Category c2 : c.getCategories()) {
                JpaSubCategory sub = new JpaSubCategory();
                sub.setId_foursquare(c2.getId());
                sub.setIconPrefix(c2.getIcon().getPrefix());
                sub.setIconSuffix(c2.getIcon().getSuffix());
                sub.setName(c2.getName());
                sub.setPluralName(c2.getPluralName());
                sub.setCategory(cat);
                subList.add(sub);
            }
            cat.setSubCategories(subList);
            this.categoryService.add((C) cat);
        }

        return null;
    }

    // String s = this.foursquareClient.getFoursquareApiClient().getAuthenticationUrl();
    // String auth = this.foursquareClient.getFoursquareApiClient().getOAuthToken();
    // Result<CompleteUser> user;
    // try {
    // user = this.foursquareClient.getFoursquareApiClient().user(null);
    // } catch (FoursquareApiException e2) {
    // // TODO Auto-generated catch block
    // e2.printStackTrace();
    // }
    // try {
    // this.foursquareClient.getFoursquareApiClient()
    // .authenticateCode("IZLIKPXO2RBZONNIUHBGOWNEVDHLXLBHWM23DGRWO2MJVIIP");
    // } catch (FoursquareApiException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // }
    // auth = this.foursquareClient.getFoursquareApiClient().getOAuthToken();
    // try {
    // user = this.foursquareClient.getFoursquareApiClient().user(null);
    // } catch (FoursquareApiException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // }
}
