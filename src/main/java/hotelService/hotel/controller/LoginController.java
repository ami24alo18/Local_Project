package hotelService.hotel.controller;


import hotelService.hotel.Response.PostResponse;
import hotelService.hotel.model.CredentialsDto;
import hotelService.hotel.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/addCredentials")
    public String addCredentials(@RequestBody CredentialsDto credentialsDto){
        loginService.addingCredentials(credentialsDto);
        return "Successfully added into db";
    }

    @DeleteMapping("/deleteCred")
    public PostResponse deleteCredentials(@RequestParam String userName){
        loginService.deleteCredentials(userName);
        return new PostResponse("User successfully deleted", true);
    }

    @PostMapping("/login")
    public PostResponse matchCredentials(@RequestBody CredentialsDto credentialsDto){
        if(loginService.matchCredentials(credentialsDto)){
            log.info("Credential match!");
            return new PostResponse("Credentials matched successfully", true);
        }
        return new PostResponse("Invalid userName or password", false);
    }
}
