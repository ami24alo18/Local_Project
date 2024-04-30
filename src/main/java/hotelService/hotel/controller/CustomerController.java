package hotelService.hotel.controller;

import hotelService.hotel.Response.PostResponse;
import hotelService.hotel.Response.RoomResponse;
import hotelService.hotel.entity.CustomerDetails;
import hotelService.hotel.entity.RoomStatus;
import hotelService.hotel.model.CustomerDetailsDto;
import hotelService.hotel.model.UpdateRepoDto;
import hotelService.hotel.service.PersistData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CustomerController {

    @Autowired
    PersistData persistData;


    @PostMapping("/post")
    public PostResponse postDetails(@RequestBody CustomerDetailsDto customerDetailsDto) {
        log.info("Welcome to data posting! ");
        PostResponse response = persistData.saveCustomerDetails(customerDetailsDto);
        return response;
    }

    @GetMapping("/get")
    public CustomerDetails getDetails(@RequestParam String name) {
        log.info("Welcome to get data! ");
        CustomerDetails customerDetails = persistData.getCustomerDetails(name);
        return customerDetails;
    }

    @GetMapping("/number")
    public CustomerDetails getDetailsByNumber(@RequestParam int number) {
        log.info("Welcome to get customer data by number! ");
        CustomerDetails customerDetails = persistData.getCustomerDetails(number);
        return customerDetails;
    }

    @PostMapping("/update")
    public PostResponse updateDetails(@RequestBody UpdateRepoDto updateRepoDto) {
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
