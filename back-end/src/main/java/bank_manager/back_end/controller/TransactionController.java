package bank_manager.back_end.controller;

import bank_manager.back_end.dto.TransactionDto;
import bank_manager.back_end.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.createTransaction(transactionDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getUserTransactions(@RequestAttribute("entityId") Long id){
        return new ResponseEntity<>(transactionService.getUserTransactions(id), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<TransactionDto>> getAccountTransactions(@PathVariable Long accountId) {
        return new ResponseEntity<>(transactionService.getAccountTransactions(accountId), HttpStatus.OK);
    }
}
