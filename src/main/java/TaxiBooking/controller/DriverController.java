package TaxiBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TaxiBooking.entity.Driver;
import TaxiBooking.repository.DriverRepository;

@RestController
@RequestMapping("/drivers")
public class DriverController {
	 @Autowired
	    private DriverRepository repo;

	    @PostMapping
	    public Driver addDriver(@RequestBody Driver driver) {
	        return repo.save(driver);
	    }

	    @GetMapping
	    public List<Driver> getAll() {
	        return repo.findAll();
	    }
}
