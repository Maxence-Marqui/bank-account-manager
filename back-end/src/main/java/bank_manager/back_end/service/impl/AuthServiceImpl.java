package bank_manager.back_end.service.impl;

import bank_manager.back_end.dto.AdminDto;
import bank_manager.back_end.dto.UserDto;
import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.Admin;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.entity.UsersAccounts;
import bank_manager.back_end.enums.EntityStatus;
import bank_manager.back_end.enums.EntityType;
import bank_manager.back_end.mappers.impl.AdminMapper;
import bank_manager.back_end.mappers.impl.UserMapper;
import bank_manager.back_end.repository.AccountRepository;
import bank_manager.back_end.repository.AdminRepository;
import bank_manager.back_end.repository.UserRepository;
import bank_manager.back_end.repository.UsersAccountsRepository;
import bank_manager.back_end.service.AuthService;
import bank_manager.back_end.utils.JwtUtils;
import bank_manager.back_end.utils.PasswordUtils;
import bank_manager.back_end.utils.RandomUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UsersAccountsRepository usersAccountsRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Map<String, Object> login(EntityType entityType, String email, String password) throws AuthenticationException {
        Object loggedEntity = new Object();
        Object loggedEntityDto = new Object();
        String hashedPassword = null;
        Long entityId = null;

        if(entityType == EntityType.USER){
            loggedEntity = userRepository.findByEmail(email);
            if(loggedEntity == null) {
                throw  new EntityNotFoundException("User not found with email: " + email);
            }
            entityId = ((User) loggedEntity).getId();
            hashedPassword = ((User) loggedEntity).getPassword();
            loggedEntityDto = UserMapper.toDto((User) loggedEntity);
        }
        if (entityType == EntityType.ADMIN){
            loggedEntity = adminRepository.findByEmail(email);
            if(loggedEntity == null) {
                throw  new EntityNotFoundException("Admin not found with email: " + email);
            }
            entityId = ((Admin) loggedEntity).getId();
            hashedPassword = ((Admin) loggedEntity).getPassword();
            loggedEntityDto = AdminMapper.toDto((Admin) loggedEntity);
        }

        if(password == null && hashedPassword == null){
            throw new AuthenticationException("Error in email or password");
        }

        if(!PasswordUtils.checkMatchingPassword(password, hashedPassword)){
            throw new AuthenticationException("Error in email or password");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("token", JwtUtils.generateToken(entityId, entityType.toString()));
        response.put("entity", loggedEntityDto);

        return response;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setStatus(EntityStatus.ACTIVE);
        User user = UserMapper.toUser(userDto, new ArrayList<>());

        User savedUser = userRepository.save(user);

        Account account = new Account();
        account.setAccountName("Mon premier compte");
        account.setStatus(EntityStatus.ACTIVE);
        account.setBalance(0.00);
        account.setMainUser(savedUser);
        account.setAccountNumber(RandomUtils.generateUniqueAccountNumber());

        Account savedAccount = accountRepository.save(account);

        UsersAccounts usersAccounts = new UsersAccounts();
        usersAccounts.setUser(user);
        usersAccounts.setAccount(savedAccount);
        usersAccounts.setJoinedAt(LocalDate.now());

        usersAccountsRepository.save(usersAccounts);

        return UserMapper.toDto(savedUser);
    }

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Admin admin = AdminMapper.toAdmin(adminDto);
        Admin savedAdmin = adminRepository.save(admin);
        return AdminMapper.toDto(savedAdmin);
    }
}
