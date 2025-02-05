package bank_manager.back_end.mappers.impl;

import bank_manager.back_end.dto.AdminDto;
import bank_manager.back_end.entity.Admin;
import bank_manager.back_end.utils.PasswordUtils;

import java.util.List;
import java.util.stream.Collectors;


public class AdminMapper {

    public static Admin toAdmin(AdminDto adminDto){
     return new Admin(
             adminDto.getId(),
             adminDto.getAdminTier(),
             adminDto.getEmail(),
             PasswordUtils.hashPassword(adminDto.getPassword()),
             adminDto.getStatus()
     );
    }

    public static AdminDto toDto(Admin admin){
        return new AdminDto(
                admin.getId(),
                admin.getAdminTier(),
                admin.getEmail(),
                admin.getStatus(),
                admin.getPassword()
        );
    }

    public static List<AdminDto> toDtoList(List<Admin> adminList){
        return adminList.stream().map(AdminMapper::toDto).collect(Collectors.toList());
    }

}
