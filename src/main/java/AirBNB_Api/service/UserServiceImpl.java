package AirBNB_Api.service;

import AirBNB_Api.entity.AppUser;
import AirBNB_Api.paylod.UserDto;
import AirBNB_Api.repo.AppUserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{


    private AppUserRepository userRepository;
    public UserServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDto addUser(UserDto userDto) {
        AppUser user = mapToEntity(userDto);
        AppUser savedUser = userRepository.save(user);
        UserDto dto = mapToDto(savedUser);
        return dto;
    }





    UserDto mapToDto(AppUser user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmailId(user.getEmailId());
        return dto;
    }
    AppUser mapToEntity(UserDto userDto){
        AppUser user = new AppUser();
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
