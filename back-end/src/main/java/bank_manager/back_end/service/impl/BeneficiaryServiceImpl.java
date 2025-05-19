package bank_manager.back_end.service.impl;

import bank_manager.back_end.dto.BeneficiaryDto;
import bank_manager.back_end.entity.Account;
import bank_manager.back_end.entity.Beneficiary;
import bank_manager.back_end.entity.User;
import bank_manager.back_end.mappers.impl.BeneficiaryMapper;
import bank_manager.back_end.repository.AccountRepository;
import bank_manager.back_end.repository.BeneficiaryRepository;
import bank_manager.back_end.repository.UserRepository;
import bank_manager.back_end.service.BeneficiaryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private final BeneficiaryRepository beneficiaryRepository;
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final UserRepository userRepository;

    public List<BeneficiaryDto> addUserBeneficiary(Long userId, BeneficiaryDto beneficiaryDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Optional<Account> internalAccount = accountRepository.findByAccountNumber(beneficiaryDto.getAccountNumber());

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setAccountName(beneficiaryDto.getAccountName());
        beneficiary.setUser(user);
        beneficiary.setAccountNumber(beneficiaryDto.getAccountNumber());
        beneficiary.setAddedAt(LocalDate.now());
        internalAccount.ifPresent(beneficiary::setInternalAccount);

        beneficiaryRepository.save(beneficiary);
        return BeneficiaryMapper.toDtoList(beneficiaryRepository.findByUser(user));
    }

    @Override
    public BeneficiaryDto getBeneficiary(Long beneficiaryId) {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(() -> new EntityNotFoundException("Beneficiary not found"));
        return BeneficiaryMapper.toDto(beneficiary);
    }

    @Override
    public BeneficiaryDto editBeneficiary(Long beneficiaryId, BeneficiaryDto beneficiaryDto) {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
                .orElseThrow(() -> new EntityNotFoundException("Beneficiary not found"));

        Optional<Account> internalAccount = accountRepository.findByAccountNumber(beneficiaryDto.getAccountNumber());
        beneficiary.setAccountName(beneficiaryDto.getAccountName());
        beneficiary.setAccountNumber(beneficiaryDto.getAccountNumber());
        internalAccount.ifPresent(beneficiary::setInternalAccount);

        Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
        return BeneficiaryMapper.toDto(savedBeneficiary);
    }

    public List<BeneficiaryDto> getUserBeneficiaries(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return BeneficiaryMapper.toDtoList(beneficiaryRepository.findByUser(user));
    }

    public void removeUserBeneficiary(Long beneficiaryId) {
        beneficiaryRepository.deleteById(beneficiaryId);
    }

    @Override
    public List<BeneficiaryDto> getLatestBeneficiaries(Long userId) {
        return BeneficiaryMapper.toDtoList(beneficiaryRepository.findLatestBeneficiaries(userId, PageRequest.of(0, 3)));
    }
}
