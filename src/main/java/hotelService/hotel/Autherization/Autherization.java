package hotelService.hotel.Autherization;

import hotelService.hotel.service.EncryptDecrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Autherization {

    @Autowired
    private EncryptDecrypt encryptDecrypt;

    public String isRequestAutherized(String authToken) {
        if (authToken == null || authToken.isEmpty()) {
            return null;
        }
        String userName = encryptDecrypt.decrypt(authToken);
        return userName;
    }
}
