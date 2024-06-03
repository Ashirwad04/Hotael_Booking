package AirBNB_Api.controller;


import AirBNB_Api.entity.AppUser;
import AirBNB_Api.entity.Booking;
import AirBNB_Api.entity.Property;
import AirBNB_Api.repo.BookingRepository;
import AirBNB_Api.repo.PropertyRepository;
import AirBNB_Api.service.PDFService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {


    private BookingRepository bookingRepository;
    private PropertyRepository propertyRepository;
    private PDFService pdfService;


    public BookingController(BookingRepository bookingRepository, PropertyRepository propertyRepository, PDFService pdfService) {
        this.bookingRepository = bookingRepository;
        this.propertyRepository = propertyRepository;
        this.pdfService = pdfService;
    }







    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createBooking(
            @RequestParam long propertyId,
            @RequestBody Booking booking,
            @AuthenticationPrincipal AppUser user
    ){
        Property property = propertyRepository.findById(propertyId).get();
        int nightlyPrice = property.getNightlyPrice();
        int totalPrice= nightlyPrice*booking.getTotalNights();
        //double tax=totalPrice*(18/100);

        booking.setTotalPrice(totalPrice);
        booking.setProperty(property);

        booking.setAppUser(user);
        Booking savedBooking = bookingRepository.save(booking);


        String filePath = pdfService.generateBookingDetailsPdf(savedBooking);

        // System.out.println(filePath);


//        if (filePath != null) {
//
//            try {
//                MultipartFile fileMultipart = BookingController.convert(filePath);
//                String fileUploadedUrl = bucketService.uploadFile(fileMultipart, "myairbnb963");
//                System.out.println(fileUploadedUrl);
//                sendMessage(fileUploadedUrl);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


        return  new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }























//    @PostMapping("/createBooking")
//    public ResponseEntity<Booking> createBooking(
//            @RequestParam long propertyId,
//            @RequestBody Booking booking,
//            @AuthenticationPrincipal AppUser user
//    ){
//        Property property = propertyRepository.findById(propertyId).get();
//        int nightlyPrice = property.getNightlyPrice();
//
//        int totalPrice=nightlyPrice*booking.getTotalNights();
//       // double tax=totalPrice*(18/100);
//
//        booking.setTotalPrice(totalPrice);
//
//        booking.setProperty(property);
//
//        booking.setAppUser(user);
//        Booking saveBooking = bookingRepository.save(booking);
//        return new ResponseEntity<>(saveBooking, HttpStatus.CREATED);
//    }







}
