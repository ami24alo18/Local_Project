package hotelService.hotel.controller;

import hotelService.hotel.Autherization.Autherization;
import hotelService.hotel.Response.PostResponse;
import hotelService.hotel.Response.RoomResponse;
import hotelService.hotel.entity.CustomerDetails;
import hotelService.hotel.model.CustomerDetailsDto;
import hotelService.hotel.model.UpdateRepoDto;
import hotelService.hotel.repository.LoginCredentialsRepository;
import hotelService.hotel.service.PersistData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    private PersistData persistData;

    @Autowired
    private Autherization autherization;

    @Autowired
    private LoginCredentialsRepository loginCredentialsRepository;


    @PostMapping("/post")
    public PostResponse postCustomerDetails(@RequestBody CustomerDetailsDto customerDetailsDto) {
//        String autherizationToken = request.getHeader("Autherization");
//        String userName = autherization.isRequestAutherized(autherizationToken);
//        String dbUserName = loginCredentialsRepository.getUserName(userName);
//        if (dbUserName.isEmpty()){
////            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            return new PostResponse("Unautherized", false);
//        }
        log.info("Welcome to data posting! ");
        PostResponse response = persistData.saveCustomerDetails(customerDetailsDto);
        return response;
    }

    @GetMapping("/get")
    public CustomerDetails getCustomerDetails(@RequestParam String name) {
        log.info("Welcome to get data! ");
//        String autherizationToken = request.getHeader("Autherization");
//        String userName = autherization.isRequestAutherized(autherizationToken);
//        String dbUserName = loginCredentialsRepository.getUserName(userName);
//        if (dbUserName.isEmpty()) {
////            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
////            return new PostResponse("Unautherized", false);
//        }
        CustomerDetails customerDetails = persistData.getCustomerDetails(name);
        return customerDetails;
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
