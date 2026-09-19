package TaxiBooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ride {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pickupLocation;
    private String dropLocation;
    private double fare;
    private String status;
    private double distance;
    private String type;
    

    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	

    @ManyToOne
    private User user;
    @ManyToOne
    private RideType rideType;
    
    @ManyToOne
    private Driver driver;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public String getDropLocation() {
		return dropLocation;
	}
	public void setDropLocation(String dropLocation) {
		this.dropLocation = dropLocation;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public RideType getRideType() {
		return rideType;
	}
	public void setRideType(RideType rideType) {
		this.rideType = rideType;
	}
	public Driver getDriver() {
	    return driver;
	}

	public void setDriver(Driver driver) {
	    this.driver = driver;
	}

	
}
