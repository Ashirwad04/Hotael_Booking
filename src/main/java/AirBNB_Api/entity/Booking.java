package AirBNB_Api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "guest_name", nullable = false)
    private String guestName;

    @Column(name = "total_nights", nullable = false)
    private Integer totalNights;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Column(name = "check_in_t_ime", nullable = false)
    private Integer checkInTIme;





    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

}