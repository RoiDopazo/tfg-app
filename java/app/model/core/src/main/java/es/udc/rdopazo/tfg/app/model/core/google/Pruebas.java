package es.udc.rdopazo.tfg.app.model.core.google;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

@Service
public class Pruebas {

    public void test(String lugar) {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyD4pnxSHaCgkAvnE1xE9z1R87sYAc32LfU").build();
        GeocodingResult[] result = null;
        try {
            result = GeocodingApi.newRequest(context).address(lugar).await();
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result[0].geometry.location.lat);
        PlacesApi.nearbySearchQuery(context, null);
    }
}
