package TaxiBooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Driver 
{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String vehicleNumber;
	    private String carType;
	    private String location;
	    private String availability; // AVAILABLE / BOOKED
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVehicleNumber() {
			return vehicleNumber;
		}
		public void setVehicleNumber(String vehicleNumber) {
			this.vehicleNumber = vehicleNumber;
		}
		public String getCarType() {
			return carType;
		}
		public void setCarType(String carType) {
			this.carType = carType;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getAvailability() {
			return availability;
		}
		public void setAvailability(String availability) {
			this.availability = availability;
		}
     

}
