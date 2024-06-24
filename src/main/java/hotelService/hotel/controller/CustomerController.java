package hotelService.hotel.controller;

import hotelService.hotel.Autherization.Autherization;
import hotelService.hotel.Response.PostResponse;
import hotelService.hotel.Response.RoomResponse;
import hotelService.hotel.entity.CustomerDetails;
import hotelService.hotel.model.CustomerDetailsDto;
import hotelService.hotel.model.UpdateRepoDto;
import hotelService.hotel.repository.LoginCredentialsRepository;
import hotelService.hotel.service.JwtService;
import hotelService.hotel.service.PersistData;
import hotelService.hotel.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    private PersistData persistData;

    @Autowired
    private Autherization autherization;

    @Autowired
    private LoginCredentialsRepository loginCredentialsRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/postCustomerDetails")
    public PostResponse postCustomerDetails(@RequestBody CustomerDetailsDto customerDetailsDto, @Autowired HttpServletRequest request) {
        String authorizationToken = request.getHeader("Authorization");
        String userId = jwtUtil.isAuthorized(authorizationToken);
        if (userId == null) {
            return new PostResponse("User authentication failed", false);
        }

        log.info("Welcome to data posting! ");
        PostResponse response = persistData.saveCustomerDetails(customerDetailsDto, userId);
        return response;
    }

    @GetMapping("/customerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestParam String name, @Autowired HttpServletRequest request) {
        log.info("Welcome to get data! ");
        String authorizationToken = request.getHeader("Authorization");
        String userId = jwtUtil.isAuthorized(authorizationToken);
        if (userId == null) {
            return ResponseEntity.ok(new PostResponse("Authorization failed", false));
        }
        CustomerDetails customerDetails = persistData.getCustomerDetails(name);
        return ResponseEntity.ok(customerDetails);
    }

    @GetMapping("/number")
    public CustomerDetails getCustomerDetailsByNumber(@RequestParam int number) {
        log.info("Welcome to get customer data by number! ");
        CustomerDetails customerDetails = persistData.getCustomerDetails(number);
        return customerDetails;
    }

    @PostMapping("/update")
    public PostResponse updateCustomerDetails(@RequestBody UpdateRepoDto updateRepoDto) {
        log.info("Welcome to update data! ");
        PostResponse response = persistData.updateCustomerDetails(updateRepoDto);
        return response;
    }

    @GetMapping("/available")
    public RoomResponse availableRooms(@RequestParam boolean isAvailable) {
        log.info("Welcome available rooms! ");
        RoomResponse roomStatuses = persistData.avilableRooms(isAvailable);
        return roomStatuses;
    }
}
