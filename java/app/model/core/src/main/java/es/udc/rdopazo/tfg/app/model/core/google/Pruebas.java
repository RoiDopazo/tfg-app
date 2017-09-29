package es.udc.rdopazo.tfg.app.model.core.google;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;

@Service
public class Pruebas {

    public void test(String lugar) {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyD4pnxSHaCgkAvnE1xE9z1R87sYAc32LfU").build();
        GeocodingResult[] result = null;
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
        DistanceMatrix trix = null;
        try {
            trix = req.origins("Seattle").destinations("San Francisco").mode(TravelMode.DRIVING)
                    .avoid(RouteRestriction.HIGHWAYS).language("es-ES").await();
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

        System.out.println("hola");
    }
}
