package bank_manager.back_end.service;

import bank_manager.back_end.dto.TransactionDto;
import bank_manager.back_end.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> getUserTransactions(Long id);
    List<TransactionDto> getAccountTransactions(Long id);
    TransactionDto createTransaction(TransactionDto transactionDto);
    List<TransactionDto> getLatestTransactions(Long userId);

}
