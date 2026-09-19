package TaxiBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TaxiBooking.entity.Driver;
import TaxiBooking.entity.Ride;
import TaxiBooking.repository.DriverRepository;
import TaxiBooking.repository.RiderRepository;

@Service
public class RideService 
{
	

    @Autowired
    private RiderRepository rideRepo;
    
    @Autowired
    private DriverRepository driverRepo;

    public Ride bookRide(Ride ride) {
       
    	ride.setStatus("BOOKED");

        // simple fare logic
        ride.setFare(ride.getDistance() * 10);

       
        double baseFare = 50;
        double perKm = 10;

        // 🔥 dynamic pricing
        if (ride.getType().equals("Economy")) {
            perKm = 8;
        } else if (ride.getType().equals("Premium")) {
            perKm = 15;
        } else if (ride.getType().equals("Fast")) {
            perKm = 12;
        }

        double fare = baseFare + (ride.getDistance() * perKm);

        ride.setFare(fare);
        System.out.println("ride saved:" +ride.getPickupLocation());
        List<Driver> drivers = driverRepo.findByAvailableTrue();

        if (drivers.isEmpty()) {
            ride.setStatus("NO_DRIVER_AVAILABLE");
            return rideRepo.save(ride);
        }

        Driver driver = drivers.get(0);

        ride.setDriver(driver);

        driver.setAvailable(false);

        driverRepo.save(driver);

        return rideRepo.save(ride);
    }
    public List<Ride> getAllRides() {
        return rideRepo.findAll();
    }
    public void cancelRide(Long rideId) {

        Ride ride = rideRepo.findById(rideId).orElse(null);

        if (ride == null) {
            return;
        }

        ride.setStatus("CANCELLED");

        Driver driver = ride.getDriver();

        if (driver != null) {

            driver.setAvailable(true);

            driverRepo.save(driver);
        }

        rideRepo.save(ride);
    }

}
