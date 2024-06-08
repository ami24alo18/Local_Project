package hotelService.hotel.service;

import hotelService.hotel.Response.PostResponse;
import hotelService.hotel.Response.RoomResponse;
import hotelService.hotel.entity.CustomerDetails;
import hotelService.hotel.model.CustomerDetailsDto;
import hotelService.hotel.model.UpdateRepoDto;
import hotelService.hotel.repository.CustomerDetailsRepository;
import hotelService.hotel.repository.RoomAvailabilityDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class PersistData {

    @Autowired
    private CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    private RoomAvailabilityDetailsRepository roomAvailabilityDetailsRepository;



    @Transactional
    public PostResponse saveCustomerDetails(CustomerDetailsDto customerDetailsDto) {
        log.info("Data posting process initiated! ");
        try {
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setName(customerDetailsDto.getName());
            customerDetails.setEmail(customerDetailsDto.getEmail());
//            customerDetails.setNumber(customerDetailsDto.getNumber());
            customerDetails.setAadhaarNo(customerDetailsDto.getAadhaarNo());
            customerDetails.setMobileNo(customerDetailsDto.getMobileNo());
            customerDetails.setRoomNo(customerDetailsDto.getRoomNo());
            customerDetails.setLogIn(customerDetailsDto.getLog_in() != null ? customerDetailsDto.getLog_in() : LocalDateTime.now());
            customerDetails.setLogOut(customerDetailsDto.getLog_out());

            boolean isTrue = roomAvailabilityDetailsRepository.getRoomStatus(customerDetails.getRoomNo());
            if (isTrue){
                roomAvailabilityDetailsRepository.updateRoomStatus(customerDetailsDto.getRoomNo());
            }
            else {
                log.info("Room No. :" + customerDetailsDto.getRoomNo() + " Not Available");
                return new PostResponse("Data Could not be persisted due to room not available! ", false);
            }
            customerDetailsRepository.save(customerDetails);
            roomAvailabilityDetailsRepository.updateRoomStatus(customerDetailsDto.getRoomNo());
            return new PostResponse("Data successfully persisted in db: ", true);
        } catch (Exception e) {
            log.error("Exception while saving customer: ", e);
            return new PostResponse("Exception while persisting data: ", false);
        }
    }


    public CustomerDetails getCustomerDetails(String name) {
        log.info("Getting data process initiated! ");
        try {
            List<CustomerDetails> customerDetails = customerDetailsRepository.getCustomerDetailsByName(name);
            return customerDetails.get(0);
        } catch (Exception e) {
            log.error("Exception while saving customer: ", e);
            return null;
        }
    }


    public CustomerDetails getCustomerDetails(int number) {
        log.info("Getting data process initiated! ");
        try {
            CustomerDetails customerDetails = customerDetailsRepository.getCustomerDetailsByNumber(number);
            return customerDetails;
        } catch (Exception e) {
            log.error("Exception while getting customer details: ", e);
            return null;
        }
    }

    public PostResponse updateCustomerDetails(UpdateRepoDto updateRepoDto) {
        log.info("Update data process initiated! ");
        try {
            List<CustomerDetails> customerDetails = customerDetailsRepository.getCustomerDetailsByName(updateRepoDto.getName());
            if (!customerDetails.isEmpty()) {
                customerDetails.get(0).setLogOut(updateRepoDto.getLog_out() != null ? updateRepoDto.getLog_out() : LocalDateTime.now());
                customerDetailsRepository.save(customerDetails.get(0));
                return new PostResponse("Data successfully updated! ", true);
            } else {
                log.info("Given Customer not present! ");
                return new PostResponse("Customer not presnt! ", false);
            }
        } catch (Exception e) {
            log.error("Exception while saving customer: ", e);
            return new PostResponse("Data update failure! ", false);
        }
    }

    public RoomResponse avilableRooms(boolean isAvailable) {

        List<Integer> rooms = roomAvailabilityDetailsRepository.getAvailableRooms(isAvailable);

        RoomResponse response = new RoomResponse();
        response.setAvailableRooms(rooms);
        response.setNumberOfRooms(rooms.size());

        return response;
    }
}
