package hotelService.hotel.service;

import hotelService.hotel.entity.LoginDetails;
import hotelService.hotel.model.CredentialsDto;
import hotelService.hotel.repository.LoginCredentialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@Component
public class LoginService {

    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;

    public void addingCredentials(CredentialsDto credentialsDto) {

        LoginDetails loginDetails = new LoginDetails();
        loginDetails.setUserName(credentialsDto.getUserName());
        loginDetails.setPassword(credentialsDto.getPassword());
        loginCredentialsRepository.save(loginDetails);
    }

    public void deleteCredentials(String userName) {
        LoginDetails loginDetails = loginCredentialsRepository.getLoginDetailsByUserName(userName);
        loginCredentialsRepository.delete(loginDetails);
    }

    public Boolean matchCredentials(CredentialsDto credentialsDto) {
        LoginDetails loginDetails = loginCredentialsRepository.getLoginDetailsByUserName(credentialsDto.getUserName());
        if(isEmpty(loginDetails)){
            return false;
        }
        return Objects.equals(loginDetails.getUserName(), credentialsDto.getUserName()) && Objects.equals(loginDetails.getPassword(), credentialsDto.getPassword());
    }
}
