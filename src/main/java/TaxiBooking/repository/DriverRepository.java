package TaxiBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TaxiBooking.entity.Driver;
@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> 
{
	 List<Driver> findByAvailableTrue();

}
