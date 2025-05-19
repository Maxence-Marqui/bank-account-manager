package bank_manager.back_end.service;

import bank_manager.back_end.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto updateUser(Long id, UserDto userDto);
    UserDto deleteUser(Long id);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    List<UserDto> getUserListById(List<Long> ids);

}
