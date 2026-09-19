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
import TaxiBooking.entity.User;
import TaxiBooking.repository.RideTypeRepository;
import TaxiBooking.service.RideService;
import jakarta.servlet.http.HttpSession;

@Controller
public class RideUIController {
	@Autowired
    private RideTypeRepository repo;

    @Autowired
    private RideService service;

    // Show ride types
    @GetMapping("/ride-type/{type}")
    public String rideTypePage(@PathVariable String type, Model model) {
        model.addAttribute("type", type);
        return "ride-details";  // your HTML file name
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
                          HttpSession session,
                          Model model) {

        Ride ride = new Ride();

        ride.setPickupLocation(pickupLocation);
        ride.setDropLocation(dropLocation);
        ride.setDistance(distance);
        ride.setType(type);

        // ✅ GET USER FROM SESSION
        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/user-login";
        }
        ride.setUser(user);
        Ride savedRide = service.bookRide(ride);

        model.addAttribute("ride", savedRide);
        if(savedRide.getDriver()!=null){
            model.addAttribute("driver", savedRide.getDriver());
        }
        model.addAttribute("carName", carName);
        model.addAttribute("carImage", carImage);
        model.addAttribute("price", savedRide.getFare());

        return "success";
    }
    
    
    @GetMapping("/rides-ui")
    public String showRides(Model model, HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/user-login";  // force login
        }

        model.addAttribute("rides", repo.findAll());
        return "rides";
    } 
    
    @GetMapping("/cancel/{id}")
    public String cancelRide(@PathVariable Long id) {

        service.cancelRide(id);

        return "redirect:/rides-ui";
    }
    
    
    
    
}
