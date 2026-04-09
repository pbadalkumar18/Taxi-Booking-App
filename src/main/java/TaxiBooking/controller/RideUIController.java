package TaxiBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import TaxiBooking.entity.Ride;
import TaxiBooking.entity.RideType;
import TaxiBooking.repository.RideTypeRepository;
import TaxiBooking.service.RideService;

@Controller
public class RideUIController {
	@Autowired
    private RideTypeRepository repo;

    @Autowired
    private RideService service;

    // Show ride types
    @GetMapping("/rides-ui")
    public String showRides(Model model) {
        model.addAttribute("rides", repo.findAll());
        return "rides";
    }

    // Open booking page
    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable Long id, Model model) {
        model.addAttribute("ride", repo.findById(id).get());
        return "confirm";
    }

    // Save booking
    @PostMapping("/confirm")
    public String confirm(@RequestParam String pickupLocation,
                          @RequestParam String dropLocation,
                          @RequestParam double distance,
                          @RequestParam String type,
                          @RequestParam String carName,
                          @RequestParam String carImage,
                          Model model) {

        Ride ride = new Ride();

        ride.setPickupLocation(pickupLocation);
        ride.setDropLocation(dropLocation);
        ride.setDistance(distance);
        ride.setType(type);

        Ride savedRide = service.bookRide(ride);

        // 👇 PASS DATA
        model.addAttribute("carName", carName);
        model.addAttribute("carImage", carImage);
        model.addAttribute("price", savedRide.getFare());   // ⭐ IMPORTANT

        return "success";
    }
    
    @GetMapping("/ride-type/{type}")
    public String rideTypePage(@PathVariable String type, Model model) {

        model.addAttribute("type", type);

        return "ride-details";
    }
    
    
    
    
    
    
    
    
    
    
}
