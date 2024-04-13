package hotelService.hotel.repository;

import hotelService.hotel.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Integer> {

    @Query(value = "SELECT * FROM customer.customer_details WHERE name = :name order by id desc;", nativeQuery = true)
    List<CustomerDetails> getCustomerDetailsByName(@Param("name") String name);

    @Query(value = "SELECT * FROM customer.customer_details WHERE number = :number order by id desc limit 1", nativeQuery = true)
    CustomerDetails getCustomerDetailsByNumber(@Param("number") int number);
}
