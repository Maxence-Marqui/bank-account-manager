package bank_manager.back_end.mappers.impl;

import bank_manager.back_end.dto.BeneficiaryDto;
import bank_manager.back_end.entity.Beneficiary;

import java.util.List;
import java.util.stream.Collectors;

public class BeneficiaryMapper {
    public static Beneficiary toBeneficiary(BeneficiaryDto beneficiaryDto){
        return new Beneficiary(
                beneficiaryDto.getId(),
                beneficiaryDto.getAccountName(),
                beneficiaryDto.getUser(),
                beneficiaryDto.getAccountNumber(),
                beneficiaryDto.getInternalAccount(),
                beneficiaryDto.getAddedAt()
        );
    }

    public static BeneficiaryDto toDto (Beneficiary beneficiary){
        return new BeneficiaryDto(
                beneficiary.getId(),
                beneficiary.getAccountName(),
                beneficiary.getUser(),
                beneficiary.getAccountNumber(),
                beneficiary.getInternalAccount(),
                beneficiary.getAddedAt()
        );
    }

    public static List<BeneficiaryDto> toDtoList(List<Beneficiary> beneficiaryList){
        return beneficiaryList.stream().map(BeneficiaryMapper::toDto).collect(Collectors.toList());
    }
}
