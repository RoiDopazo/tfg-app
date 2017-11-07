package es.udc.rdopazo.tfg.app.model.core.google.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import es.udc.rdopazo.tfg.app.model.core.google.GoogleService;
import es.udc.rdopazo.tfg.app.model.core.util.GoogleClient;

@Service
public class GoogleServiceImpl implements GoogleService {

    @Autowired
    private GoogleClient googleClient;

    public DistanceMatrix getTravelInfo(double arg1, double arg2, double arg3, double arg4, String travelMode) {
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(this.googleClient.getGoogleApiContext());
        DistanceMatrix trix = null;
        LatLng ori = new LatLng(arg1, arg2);
        LatLng dest = new LatLng(arg3, arg4);
        TravelMode tMode = TravelMode.valueOf(travelMode);
        try {
            trix = req.origins(ori).destinations(dest).mode(tMode).language("es-ES").await();
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
        return trix;
    }

}
