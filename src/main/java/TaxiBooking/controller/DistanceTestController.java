package TaxiBooking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import TaxiBooking.service.DistanceService;

@RestController
public class DistanceTestController {
	private final DistanceService distanceService;

    public DistanceTestController(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    @GetMapping("/test-distance")
    public String testDistance() throws Exception {

        double distance =
                distanceService.calculateDistance(
                        "Marathahalli, Bangalore",
                        "Whitefield, Bangalore"
                );

        return "Distance = " + distance + " km";
    }
}
