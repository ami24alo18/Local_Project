package hotelService.hotel.Response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostResponse {

    private String message;
    private boolean success;


    public PostResponse(String message, boolean success) {
        this.success = success;
        this.message = message;
    }
}
