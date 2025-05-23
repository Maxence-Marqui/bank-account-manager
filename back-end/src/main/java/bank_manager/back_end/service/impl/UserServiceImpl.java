package bank_manager.back_end.service.impl;

import bank_manager.back_end.dto.AccountDto;
import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;
import bank_manager.back_end.enums.EntityStatus;
import bank_manager.back_end.mappers.impl.AccountMapper;
import bank_manager.back_end.mappers.impl.UserMapper;
import bank_manager.back_end.repository.AccountRepository;
import bank_manager.back_end.repository.UserRepository;
import bank_manager.back_end.repository.UsersAccountsRepository;
import bank_manager.back_end.service.UserService;
import bank_manager.back_end.service.UsersAccountsService;
import bank_manager.back_end.utils.PasswordUtils;
import bank_manager.back_end.utils.RandomUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private UsersAccountsService usersAccountsService;

    @Autowired
    private UsersAccountsRepository usersAccountsRepository;


    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        if (userDto.getFirstName() != null){
            user.setFirstName(userDto.getFirstName());
        }

        if (userDto.getLastName() != null){
            user.setLastName(userDto.getLastName());
        }

        if (userDto.getEmail() != null){
            user.setEmail(userDto.getEmail());
        }

        if (userDto.getPassword() != null){
            user.setPassword(PasswordUtils.hashPassword(userDto.getPassword()));
        }

        if (userDto.getBirthday() != null ){
            user.setBirthday(userDto.getBirthday());
        }

        if (userDto.getPhoneNumber() != null){
            user.setPhoneNumber(userDto.getPhoneNumber());
        }

        if (userDto.getStatus() != null){
            user.setStatus(userDto.getStatus());
        }

        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        user.setStatus(EntityStatus.ARCHIVED);
        User archivedUser = userRepository.save(user);
        return UserMapper.toDto(archivedUser);

    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDto> getUserListById(List<Long> ids) {
        List<User> userList = userRepository.findAllById(ids);
        if(userList.isEmpty()) return Collections.emptyList();
        return UserMapper.toDtoList(userList);
    }
}
