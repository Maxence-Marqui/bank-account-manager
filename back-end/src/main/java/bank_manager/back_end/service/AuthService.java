package bank_manager.back_end.service;

import bank_manager.back_end.dto.AdminDto;
import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.enums.EntityType;

import javax.naming.AuthenticationException;
import java.util.Map;


public interface AuthService {
    public Map<String, Object> login(EntityType entityType, String email, String password) throws AuthenticationException;

    AdminDto createAdmin(AdminDto adminDto);

    UserDto createUser(UserDto userDto);
}
