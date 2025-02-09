package bank_manager.back_end.mappers.impl;

import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;
import bank_manager.back_end.repository.UserRepository;
import bank_manager.back_end.repository.UsersAccountsRepository;
import bank_manager.back_end.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(UserDto userDto, List<UsersAccounts> usersAccounts){

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
                usersAccounts
        );
    }

    public static UserDto toDto(User user){

        List<Long> accountIds = user.getUserAccounts().stream()
                .map(usersAccounts -> usersAccounts.getAccount().getId())
                .toList();

        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday(),
                user.getPhoneNumber(),
                user.getStatus(),
                user.getFlagId(),
                accountIds,
                user.getPassword()
        );
    }

    public static List<UserDto> toDtoList(List<User> userList){
        return userList.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
}
