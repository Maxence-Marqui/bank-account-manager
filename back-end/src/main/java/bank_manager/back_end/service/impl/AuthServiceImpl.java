package bank_manager.back_end.service.impl;

import bank_manager.back_end.entity.Admin;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.enums.EntityType;
import bank_manager.back_end.repository.AdminRepository;
import bank_manager.back_end.repository.UserRepository;
import bank_manager.back_end.service.AuthService;
import bank_manager.back_end.utils.JwtUtils;
import bank_manager.back_end.utils.PasswordUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public String login(EntityType entityType, String email, String password) throws AuthenticationException {
        String hashedPassword = null;
        Long entityId = null;

        if(entityType == EntityType.USER){
            User loggedEntity = userRepository.findByEmail(email);
            if(loggedEntity == null) {
                throw  new EntityNotFoundException("User not found with email: " + email);
            }
            entityId = loggedEntity.getId();
            hashedPassword = loggedEntity.getPassword();
        }
        if (entityType == EntityType.ADMIN){
            Admin loggedEntity = adminRepository.findByEmail(email);
            if(loggedEntity == null) {
                throw  new EntityNotFoundException("Admin not found with email: " + email);
            }
            entityId = loggedEntity.getId();
            hashedPassword = loggedEntity.getPassword();
        }

        if(password == null && hashedPassword == null){
            throw new AuthenticationException("Error in email or password");
        }

        if(!PasswordUtils.checkMatchingPassword(password, hashedPassword)){
            throw new AuthenticationException("Error in email or password");
        }

        return JwtUtils.generateToken(entityId, entityType.toString());
    }
}
