package bank_manager.back_end.mappers.impl;

import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.utils.PasswordUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static User toUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                PasswordUtils.hashPassword(userDto.getPassword()),
                userDto.getBirthday(),
                userDto.getPhoneNumber(),
                userDto.getFlagId(),
                userDto.getStatus(),
                userDto.getUserAccounts()
        );
    }

    public static UserDto toDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday(),
                user.getPhoneNumber(),
                user.getStatus(),
                user.getFlagId(),
                user.getUserAccounts(),
                user.getPassword()
        );
    }

    public static List<UserDto> toDtoList(List<User> userList){
        return userList.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
}
