package TaxiBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import TaxiBooking.entity.User;
import TaxiBooking.repository.UserRepository;

@Controller
public class AuthController 
{
	 @Autowired
	    private UserRepository userRepo;
	
	// STEP 1: Role selection
    @PostMapping("/login")
    public String login(@RequestParam String role) {

        if (role.equals("ADMIN")) {
            return "redirect:/admin";
        } else {
            return "redirect:/user-login";   // go to user login form
        }
    }
    
 // STEP 2: Show user login page
    @GetMapping("/user-login")
    public String userLoginPage() {
        return "user-login";
    }

    // STEP 3: Handle user login
    @PostMapping("/user-auth")
    public String userAuth(@RequestParam String name,
                           @RequestParam String phone) {

        User user = new User();
        user.setName(name);
        user.setPhone(phone);

        userRepo.save(user);   // save user

        return "redirect:/rides-ui";   // go to rides page
    }
	}

