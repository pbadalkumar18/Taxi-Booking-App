package TaxiBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import TaxiBooking.entity.Ride;

import TaxiBooking.repository.RiderRepository;

@Service
public class RideService 
{
	

    @Autowired
    private RiderRepository rideRepo;

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

        return rideRepo.save(ride);
    }
    public List<Ride> getAllRides() {
        return rideRepo.findAll();
    }

}
