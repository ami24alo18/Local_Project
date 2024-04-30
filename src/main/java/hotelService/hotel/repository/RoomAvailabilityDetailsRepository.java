package hotelService.hotel.repository;

import hotelService.hotel.entity.CustomerDetails;
import hotelService.hotel.entity.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomAvailabilityDetailsRepository extends JpaRepository<RoomStatus, Integer> {

    @Query(value = "SELECT room_no FROM customer.room_details WHERE availability = :isTrue", nativeQuery = true)
    List<Integer> getAvailableRooms(@Param("isTrue") boolean isTrue);
}
