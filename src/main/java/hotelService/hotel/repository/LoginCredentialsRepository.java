package hotelService.hotel.repository;

import hotelService.hotel.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginCredentialsRepository extends JpaRepository<LoginDetails, Integer> {

    LoginDetails getLoginDetailsByUserName(String userName);

    LoginDetails getLoginDetailsByUserId(String user_id);

    @Query(value = "SELECT username FROM customer.login_details WHERE username = :username order by id desc limit 1", nativeQuery = true)
    String getUserName(@Param("username") String username);
}
