package TaxiBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TaxiBooking.entity.Driver;
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{
	Driver findFirstByAvailability(String availability);
}
