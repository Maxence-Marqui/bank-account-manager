package bank_manager.back_end.service;

import bank_manager.back_end.dto.AdminDto;
import bank_manager.back_end.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    AdminDto updateAdmin(Long id, AdminDto adminDto);
    AdminDto deleteAdmin(Long id);
    List<AdminDto> getAllAdmins();
    Optional<AdminDto> getAdminById(Long id);
}
