package bank_manager.back_end.controller;

import bank_manager.back_end.dto.AdminDto;
import bank_manager.back_end.service.AdminService;
import bank_manager.back_end.service.ResetDatabaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("admins")
@RequiredArgsConstructor
@Validated
public class AdminController {

    private final AdminService adminService;
    private final ResetDatabaseService resetDatabaseService;


    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(@RequestBody @Valid AdminDto adminDto){
        return new ResponseEntity<>(this.adminService.createAdmin(adminDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdminDto>> getAllAdmins(){
        return new ResponseEntity<>(this.adminService.getAllAdmins(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AdminDto>> getAdminById(@PathVariable Long id){
        return new ResponseEntity<>(this.adminService.getAdminById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(@PathVariable Long id, @RequestBody AdminDto adminDto){
        return new ResponseEntity<>(adminService.updateAdmin(id, adminDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdminDto> deleteAdmin(@PathVariable Long id){
        return new ResponseEntity<>(adminService.deleteAdmin(id), HttpStatus.OK);
    }

    @DeleteMapping("/reset")
    public ResponseEntity<String> resetDatabase() {
        resetDatabaseService.deleteAll();
        return ResponseEntity.ok("Database has been reset to clean state");
    }

}
