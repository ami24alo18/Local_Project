package hotelService.hotel.repository;

import hotelService.hotel.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginCredentialsRepository extends JpaRepository<LoginDetails, Integer> {

    LoginDetails getLoginDetailsByUserName(String userName);
}
