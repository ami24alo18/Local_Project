package hotelService.hotel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer_details")
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "room_no", nullable = false)
    private Integer roomNo;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "aadhaar_no")
    private String aadhaarNo;

    @Column(name = "log_in")
    private LocalDateTime logIn;

    @Column(name = "log_out")
    private LocalDateTime logOut;
}
