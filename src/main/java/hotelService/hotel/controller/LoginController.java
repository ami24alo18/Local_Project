package hotelService.hotel.controller;


import hotelService.hotel.Autherization.Autherization;
import hotelService.hotel.Response.AuthenticationResponse;
import hotelService.hotel.Response.PostResponse;
import hotelService.hotel.model.CredentialsDto;
import hotelService.hotel.service.LoginService;
import hotelService.hotel.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private Autherization autherization;

    @Autowired
    private JwtUtil jwtUtil;

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
    public AuthenticationResponse matchCredentials(@RequestBody CredentialsDto credentialsDto){
        log.info("Customer logging and validation");
        if(!loginService.matchCredentials(credentialsDto)){
            log.info("Credential match!");
            return new AuthenticationResponse("Invalid credentials", false, null);
        }
        String jwt = jwtUtil.generateToken(credentialsDto.getUserName());
        return new AuthenticationResponse("Valid user", true, jwt);
    }
}
