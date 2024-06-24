package hotelService.hotel.Response;

import lombok.Data;

import java.util.List;

@Data
public class RoomResponse {

    private List<Integer> availableRooms;
    private int numberOfRooms;
}
