package bank_manager.back_end.service.impl;

import bank_manager.back_end.dto.TransactionDto;
import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.Transaction;
import bank_manager.back_end.enums.TransactionType;
import bank_manager.back_end.mappers.impl.TransactionMapper;
import bank_manager.back_end.repository.AccountRepository;
import bank_manager.back_end.repository.TransactionRepository;
import bank_manager.back_end.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<TransactionDto> getUserTransactions(Long id) {
        List<Transaction> transactionList = transactionRepository.findByUser(id);
        return TransactionMapper.toDtoList(transactionList);
    }

    @Override
    public List<TransactionDto> getAccountTransactions(Long id) {
        List<Transaction> transactionList = transactionRepository.findBySimilarAccountName(id);
        return TransactionMapper.toDtoList(transactionList);
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = TransactionMapper.toTransaction(transactionDto);

        Account fromAccount = accountRepository.findByAccountNumber(transactionDto.getFromAccountNumber())
                .orElseThrow(() -> new EntityNotFoundException("Account not found with number: " + transactionDto.getFromAccountNumber()));

        Optional<Account> toAccount = accountRepository.findByAccountNumber(transactionDto.getToAccountNumber());

        if(toAccount.isPresent()){
            Account toNewAccount = toAccount.get();
            toNewAccount.setBalance(toNewAccount.getBalance() + transactionDto.getAmount());
            System.out.println(toNewAccount.getBalance());
            accountRepository.save(toNewAccount);
        }

        if(fromAccount != null){
            fromAccount.setBalance(fromAccount.getBalance() - transactionDto.getAmount());
            accountRepository.save(fromAccount);
        }

        transaction.setCreatedAt(LocalDate.now());
        Transaction savedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toDto(savedTransaction);
    }

    @Override
    public List<TransactionDto> getLatestTransactions(Long userId) {
        return TransactionMapper.toDtoList(
                transactionRepository.findUserLatestTransactions(
                        userId, PageRequest.of(0, 10)
                ).getContent());
    }
}
