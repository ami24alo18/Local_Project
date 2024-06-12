package hotelService.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsDto {

    private String user_id;
    private String name;
    private String email;
    @NonNull
    private String mobileNo;
    @NonNull
    private String aadhaarNo;
    @NonNull
    private Integer roomNo;
    private LocalDateTime log_in;
    private LocalDateTime log_out;
}
