package TaxiBooking.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class DistanceService 
{
	
	public String getCoordinates(String location) throws IOException, InterruptedException {

        String url = "https://nominatim.openstreetmap.org/search"
                + "?q=" + location.replace(" ", "+")
                + "&format=json"
                + "&limit=1";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "TaxiBookingApp")
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
	public double[] getLatLon(String location)
            throws IOException, InterruptedException {

        String json = getCoordinates(location);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(json);

        if (root.isEmpty()) {
            throw new RuntimeException(
                    "Location not found: " + location);
        }

        double latitude =
                root.get(0).get("lat").asDouble();

        double longitude =
                root.get(0).get("lon").asDouble();

        return new double[] {
                latitude,
                longitude
        };
    }
	public double getDistanceFromOSRM(
	        double pickupLat,
	        double pickupLon,
	        double dropLat,
	        double dropLon)
	        throws IOException, InterruptedException {

	    String url = "https://router.project-osrm.org/route/v1/driving/"
	            + pickupLon + "," + pickupLat
	            + ";"
	            + dropLon + "," + dropLat
	            + "?overview=false";

	    HttpClient client = HttpClient.newHttpClient();

	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(url))
	            .header("User-Agent", "TaxiBookingApp")
	            .GET()
	            .build();

	    HttpResponse<String> response =
	            client.send(
	                    request,
	                    HttpResponse.BodyHandlers.ofString()
	            );

	    ObjectMapper mapper = new ObjectMapper();

	    JsonNode root = mapper.readTree(response.body());

	    double distanceInMeters =
	            root.get("routes")
	                .get(0)
	                .get("distance")
	                .asDouble();

	    double distanceInKm =
	            distanceInMeters / 1000.0;

	    return distanceInKm;
	}
	public double calculateDistance(
	        String pickupLocation,
	        String dropLocation)
	        throws IOException, InterruptedException {

	    // Get pickup coordinates
	    double[] pickupCoordinates =
	            getLatLon(pickupLocation);

	    // Get drop coordinates
	    double[] dropCoordinates =
	            getLatLon(dropLocation);

	    // Extract latitude and longitude
	    double pickupLat = pickupCoordinates[0];
	    double pickupLon = pickupCoordinates[1];

	    double dropLat = dropCoordinates[0];
	    double dropLon = dropCoordinates[1];

	    // Get driving distance from OSRM
	    double distanceInKm =
	            getDistanceFromOSRM(
	                    pickupLat,
	                    pickupLon,
	                    dropLat,
	                    dropLon
	            );

	    return distanceInKm;
	}
	public void testDistance() throws Exception {

	    double distance = calculateDistance(
	            "Marathahalli, Bangalore",
	            "Whitefield, Bangalore"
	    );

	    System.out.println("Distance = " + distance + " km");
	}
}
