package TaxiBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import TaxiBooking.entity.Driver;
import TaxiBooking.entity.RideType;
import TaxiBooking.repository.DriverRepository;
import TaxiBooking.repository.RideTypeRepository;

@Controller
public class AdminController {
	@Autowired
    private DriverRepository driverRepo;

    @Autowired
    private RideTypeRepository rideTypeRepo;

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
    @PostMapping("/admin")
    public String adminLogin(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password,
                             Model model) {

        // simple validation (you can customize)
        if(email.equals("admin@gmail.com") && password.equals("1234")) {
            model.addAttribute("name", name);
            return "admin-success";
        }

        return "admin";
    }
    
}
