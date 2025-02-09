package bank_manager.back_end.service;

import bank_manager.back_end.enums.EntityType;
import bank_manager.back_end.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.AuthenticationException;


public interface AuthService {
    public String login(EntityType entityType, String email, String password) throws AuthenticationException;
}
