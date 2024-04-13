package hotelService.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailsDto {

    private String name;
    private String email;
    private int number;
    private String mobileNo;
    private String aadhaarNo;
    private LocalDateTime log_in;
    private LocalDateTime log_out;
}
