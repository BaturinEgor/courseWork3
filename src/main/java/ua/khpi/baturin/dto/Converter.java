package ua.khpi.baturin.dto;

import ua.khpi.baturin.entity.Role;
import ua.khpi.baturin.entity.User;

public class Converter {

    public static User userDtoToUser(UserDto userDto) {
        User user = new User();
        Long roleID;

        user.setId(userDto.getId());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        if (userDto.getRole().equals("ADMIN")) {
            roleID = 1L;
        } else {
            roleID = 2L;
        }
        user.setRole(new Role(roleID, userDto.getRole()));
        return user;
    }

    public static UserDto userToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setConfirmPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole().getName());
        return userDto;
    }
}
