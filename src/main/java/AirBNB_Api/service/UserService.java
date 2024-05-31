package AirBNB_Api.service;

import AirBNB_Api.paylod.LoginDto;
import AirBNB_Api.paylod.UserDto;

public interface UserService {


    public UserDto addUser(UserDto userDto);

    Boolean verifyLogin(LoginDto loginDto);
}
