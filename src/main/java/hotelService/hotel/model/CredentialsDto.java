package hotelService.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDto {

    @NonNull
    private String userId;
    @NotNull
    private String userName;
    @NonNull
    private String password;
}
