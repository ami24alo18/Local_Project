package hotelService.hotel.repository;

import hotelService.hotel.entity.RoomDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomAvailabilityDetailsRepository extends JpaRepository<RoomDetails, Integer> {

    @Query(value = "SELECT room_no FROM customer.room_details WHERE availability = :isTrue", nativeQuery = true)
    List<Integer> getAvailableRooms(@Param("isTrue") boolean isTrue);

    @Modifying
    @Query(value = "UPDATE customer.room_details SET availability = false WHERE room_no = :roomNo", nativeQuery = true)
    void updateRoomStatus(@Param("roomNo") Integer roomNo);

    @Query(value = "SELECT * FROM customer.room_details WHERE room_no = :roomNo", nativeQuery = true)
    RoomDetails getRoomStatus(@Param("roomNo") Integer roomNo);
}
