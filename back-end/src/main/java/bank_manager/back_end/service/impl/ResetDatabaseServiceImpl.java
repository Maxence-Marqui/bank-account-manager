package bank_manager.back_end.service.impl;

import bank_manager.back_end.repository.*;
import bank_manager.back_end.service.ResetDatabaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetDatabaseServiceImpl implements ResetDatabaseService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UsersAccountsRepository usersAccountsRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlagRepository flagRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UsersActionsRepository usersActionsRepository;


    @Transactional
    public void deleteAll() {

        usersActionsRepository.deleteAll();
        usersAccountsRepository.deleteAll();
        transactionRepository.deleteAll();
        flagRepository.deleteAll();
        accountRepository.deleteAll();
        userRepository.deleteAll();
        adminRepository.deleteAll();

        resetAutoIncrement();
    }


    @Transactional
    public void resetAutoIncrement() {
        try {
            usersActionsRepository.resetSequence();
            usersAccountsRepository.resetSequence();
            transactionRepository.resetSequence();
            flagRepository.resetSequence();
            accountRepository.resetSequence();
            userRepository.resetSequence();
            adminRepository.resetSequence();

        } catch (Exception e) {
            System.out.println("Erreur lors de la réinitialisation des séquences : " + e.getMessage());
        }
    }
}
