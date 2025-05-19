package bank_manager.back_end.service;

import bank_manager.back_end.dto.BeneficiaryDto;
import bank_manager.back_end.entity.Beneficiary;

import java.util.List;

public interface BeneficiaryService {
    List<BeneficiaryDto> addUserBeneficiary(Long userId, BeneficiaryDto beneficiaryDto);
    BeneficiaryDto getBeneficiary(Long beneficiaryId);
    BeneficiaryDto editBeneficiary(Long beneficiaryId, BeneficiaryDto beneficiaryDto);
    List<BeneficiaryDto> getUserBeneficiaries(Long userId);
    void removeUserBeneficiary(Long beneficiaryId);
    List<BeneficiaryDto> getLatestBeneficiaries(Long userId);
}
