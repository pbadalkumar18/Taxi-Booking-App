package TaxiBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TaxiBooking.entity.RideType;

@Repository
public interface RideTypeRepository extends JpaRepository<RideType, Long> {

}
