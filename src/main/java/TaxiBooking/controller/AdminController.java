package TaxiBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import TaxiBooking.entity.RideType;

import TaxiBooking.repository.RideTypeRepository;
import TaxiBooking.repository.RiderRepository;

@Controller
public class AdminController {

    private final HomeController homeController;
	

    @Autowired
    private RideTypeRepository rideTypeRepo;
    @Autowired
    private RiderRepository riderRepo;

    AdminController(HomeController homeController) {
        this.homeController = homeController;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
    @PostMapping("/admin-login")
    public String adminLogin(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String password,
                             Model model) {

        if(email.equals("admin@gmail.com") && password.equals("1234")) {

            model.addAttribute("name", name);

            
            model.addAttribute("rides", riderRepo.findAll());

            System.out.println("total rides :"+riderRepo.findAll().size());

            return "admin-dashboard";
        }

        return "admin";
    }
}
