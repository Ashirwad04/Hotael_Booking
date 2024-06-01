package AirBNB_Api.controller;


import AirBNB_Api.entity.AppUser;
import AirBNB_Api.paylod.JwtResponse;
import AirBNB_Api.paylod.LoginDto;
import AirBNB_Api.paylod.UserDto;
import AirBNB_Api.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/{addUser}")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {

        UserDto dto = userService.addUser(userDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDto loginDto){

        String token= userService.verifyLogin(loginDto);
        if (token !=null){
            JwtResponse response = new JwtResponse();
            response.setToken(token);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

       else {
            return new ResponseEntity<>("Fail", HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/profile")
    public AppUser getCuurentProfile(@AuthenticationPrincipal AppUser user){

        return user;
    }




}
