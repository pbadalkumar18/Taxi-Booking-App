package TaxiBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TaxiBooking.entity.Ride;

@Repository
public interface RiderRepository extends JpaRepository<Ride,Long>{

}
