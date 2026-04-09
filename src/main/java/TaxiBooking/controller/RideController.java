package TaxiBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TaxiBooking.entity.Ride;
import TaxiBooking.service.RideService;

@RestController
@RequestMapping("/rides")
public class RideController {
	@Autowired
    private RideService service;

    @PostMapping("/book")
    public Ride book(@RequestBody Ride ride) {
        return service.bookRide(ride);
    }

    @GetMapping
    public List<Ride> getAll() {
        return service.getAllRides();
    }
}
