package bank_manager.back_end.mappers.impl;

import bank_manager.back_end.dto.TransactionDto;
import bank_manager.back_end.entity.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {
    public static Transaction toTransaction(TransactionDto transactionDto){
        return new Transaction(
                transactionDto.getId(),
                transactionDto.getFromAccountNumber(),
                transactionDto.getToAccountNumber(),
                transactionDto.getAmount(),
                transactionDto.getNote(),
                transactionDto.getFlag(),
                transactionDto.getCreatedAt(),
                transactionDto.getType()
        );
    }

    public static TransactionDto toDto(Transaction transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getFromAccountNumber(),
                transaction.getToAccountNumber(),
                transaction.getAmount(),
                transaction.getNote(),
                transaction.getFlag(),
                transaction.getCreatedAt(),
                transaction.getType()
        );
    }

    public static List<TransactionDto> toDtoList(List<Transaction> transactionList){
        return transactionList.stream().map(TransactionMapper::toDto).collect(Collectors.toList());
    }
}
