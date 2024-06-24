package hotelService.hotel.Response;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class AuthenticationResponse {

    private final String message;
    private final boolean success;
    private final String jwt;

    public AuthenticationResponse(String message, boolean success, String jwt) {
        this.message = message;
        this.success = success;
        this.jwt = jwt;
    }

}