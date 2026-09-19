package TaxiBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TaxiBooking.entity.Driver;
import TaxiBooking.repository.DriverRepository;

@RestController
@RequestMapping("/driver")
public class DriverController 
{
	@Autowired
    private DriverRepository driverRepo;

    @PostMapping("/save")
    public Driver saveDriver(@RequestBody Driver driver) {
        return driverRepo.save(driver);
    }

}
