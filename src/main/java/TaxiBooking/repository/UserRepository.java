package TaxiBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TaxiBooking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
