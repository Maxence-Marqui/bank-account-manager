package bank_manager.back_end.service.impl;

import bank_manager.back_end.dto.AdminDto;
import bank_manager.back_end.entity.Admin;
import bank_manager.back_end.enums.EntityStatus;
import bank_manager.back_end.mappers.impl.AdminMapper;
import bank_manager.back_end.repository.AdminRepository;
import bank_manager.back_end.service.AdminService;
import bank_manager.back_end.utils.PasswordUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {
        Admin admin = AdminMapper.toAdmin(adminDto);
        Admin savedAdmin = adminRepository.save(admin);
        return AdminMapper.toDto(savedAdmin);
    }

    @Override
    public AdminDto updateAdmin(Long id, AdminDto adminDto) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));

        if (adminDto.getAdminTier() != null) {
            admin.setAdminTier(admin.getAdminTier());
        }

        if (adminDto.getEmail() != null) {
            admin.setEmail(adminDto.getEmail());
        }

        if (adminDto.getStatus() != null) {
            admin.setStatus(adminDto.getStatus());
        }

        if (adminDto.getPassword() != null && !adminDto.getPassword().isBlank()) {
            admin.setPassword(PasswordUtils.hashPassword(adminDto.getPassword()));
        }

        Admin updatedAdmin = adminRepository.save(admin);
        return AdminMapper.toDto(updatedAdmin);
    }

    @Override
    public AdminDto deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));

        admin.setStatus(EntityStatus.ARCHIVED);
        Admin archivedAdmin = adminRepository.save(admin);
        return AdminMapper.toDto(archivedAdmin);
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return AdminMapper.toDtoList(adminRepository.findAll());
    }

    @Override
    public Optional<AdminDto> getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found with id: " + id));
        AdminDto adminDto = AdminMapper.toDto(admin);
        return Optional.of(adminDto);
    }
}
