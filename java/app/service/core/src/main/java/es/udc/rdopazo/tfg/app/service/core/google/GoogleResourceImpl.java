package es.udc.rdopazo.tfg.app.service.core.google;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.maps.model.DistanceMatrix;

import es.udc.rdopazo.tfg.app.model.core.google.GoogleService;
import es.udc.rdopazo.tfg.service.api.google.GoogleResource;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;

@Service
public class GoogleResourceImpl implements GoogleResource {

    @Autowired
    private GoogleService googleService;

    public List<StayDto> getTravelInfoBatch(List<StayDto> stays) {

        for (int i = 0; i < stays.size(); i++) {
            if (i != (stays.size() - 1)) {
                DistanceMatrix distanceMatrix = this.googleService.getTravelInfo(stays.get(i).getPlace().getLat(),
                        stays.get(i).getPlace().getLng(), stays.get(i + 1).getPlace().getLat(),
                        stays.get(i + 1).getPlace().getLng(), stays.get(i).getTravelMode());
                if (distanceMatrix.rows.length > 0) {
                    if (distanceMatrix.rows[0].elements.length > 0) {
                        stays.get(i).setTravelDistance(distanceMatrix.rows[0].elements[0].distance.inMeters);
                        stays.get(i).setTravelTime(distanceMatrix.rows[0].elements[0].duration.inSeconds * 1000);
                    }
                }
            }
        }
        return stays;
    }

    public List<Long> getTravelInfo(String oriLat, String oriLng, String dstLat, String dstLng, String travelMode) {
        Double oriLatLong = null;
        Double oriLngLong = null;
        Double dstLatLong = null;
        Double dslLngLong = null;
        List<Long> returnList = new ArrayList<Long>();
        try {
            oriLatLong = Double.parseDouble(oriLat);
        } catch (NumberFormatException e) {

        }
        try {
            oriLngLong = Double.parseDouble(oriLng);
        } catch (NumberFormatException e) {

        }
        try {
            dstLatLong = Double.parseDouble(dstLat);
        } catch (NumberFormatException e) {

        }
        try {
            dslLngLong = Double.parseDouble(dstLng);
        } catch (NumberFormatException e) {

        }
        DistanceMatrix distanceMatrix = this.googleService.getTravelInfo(oriLatLong, oriLngLong, dstLatLong, dslLngLong,
                travelMode);
        if (distanceMatrix.rows.length > 0) {
            if (distanceMatrix.rows[0].elements.length > 0) {
                returnList.add(distanceMatrix.rows[0].elements[0].distance.inMeters);
                returnList.add(distanceMatrix.rows[0].elements[0].duration.inSeconds * 1000);
            }
        }
        return returnList;
    }

}
