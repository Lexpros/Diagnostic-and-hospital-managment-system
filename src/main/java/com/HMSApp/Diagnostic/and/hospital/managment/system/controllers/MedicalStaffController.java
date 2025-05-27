package com.HMSApp.Diagnostic.and.hospital.managment.system.controllers;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.MedicalStaff;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/staff")
public class MedicalStaffController {

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    @GetMapping("/all")
    public List<MedicalStaff> getAllMedicalStaff() {
        return medicalStaffRepository.findAll();
    }

    @PostMapping("/create")
    public MedicalStaff createMedicalStaff(@RequestBody MedicalStaff medicalStaff) {
        return medicalStaffRepository.save(medicalStaff);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalStaff> getMedicalStaffById(@PathVariable Long id) throws AttributeNotFoundException {
        MedicalStaff staff = medicalStaffRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Сотрудник с id " + id + " не найден"));
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalStaff> updateMedicalStaff(@PathVariable Long id, @RequestBody MedicalStaff staffDetails)
            throws AttributeNotFoundException {
        MedicalStaff staff = medicalStaffRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Сотрудник с id " + id + " не найден"));

        staff.setLastName(staffDetails.getLastName());
        staff.setFirstName(staffDetails.getFirstName());
        staff.setMiddleName(staffDetails.getMiddleName());
        staff.setSpecialty(staffDetails.getSpecialty());
        staff.setContactInfo(staffDetails.getContactInfo());
        staff.setLogin(staffDetails.getLogin());
        staff.setPasswordHash(staffDetails.getPasswordHash());
        staff.setFacility(staffDetails.getFacility());

        MedicalStaff updatedStaff = medicalStaffRepository.save(staff);
        return ResponseEntity.ok(updatedStaff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteMedicalStaff(@PathVariable Long id)
            throws AttributeNotFoundException {
        MedicalStaff staff = medicalStaffRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Сотрудник с id " + id + " не найден"));

        medicalStaffRepository.delete(staff);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
