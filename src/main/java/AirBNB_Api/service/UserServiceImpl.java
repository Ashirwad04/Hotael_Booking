package AirBNB_Api.service;

import AirBNB_Api.entity.AppUser;
import AirBNB_Api.paylod.LoginDto;
import AirBNB_Api.paylod.UserDto;
import AirBNB_Api.repo.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{


    private JWTService jwtService;
    private AppUserRepository userRepository;
    public UserServiceImpl(JWTService jwtService, AppUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

//add user
    @Override
    public UserDto addUser(UserDto userDto) {
        AppUser user = mapToEntity(userDto);
        AppUser savedUser = userRepository.save(user);
        UserDto dto = mapToDto(savedUser);
        return dto;
    }


//verify login

    @Override
    public String verifyLogin(LoginDto loginDto) {

        Optional<AppUser> opUser = userRepository.findByUsername(loginDto.getUsername());
        System.out.println(opUser);
        if (opUser.isPresent()){
            AppUser user = opUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(),user.getPassword())){
               return jwtService.generateToken(user);
            }
        }

        return null;
    }







    UserDto mapToDto(AppUser user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUserRole(user.getUserRole());
        dto.setUsername(user.getUsername());
        dto.setEmailId(user.getEmailId());
        return dto;
    }
    AppUser mapToEntity(UserDto userDto){
        AppUser user = new AppUser();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());

        user.setEmailId(userDto.getEmailId());
        user.setUserRole(userDto.getUserRole());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt(10)));
        //user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        return user;
    }
}
