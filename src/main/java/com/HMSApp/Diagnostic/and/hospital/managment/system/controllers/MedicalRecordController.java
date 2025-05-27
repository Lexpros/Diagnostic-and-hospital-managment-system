package com.HMSApp.Diagnostic.and.hospital.managment.system.controllers;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.MedicalRecord;
import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.MedicalRecordDTO;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RequestMapping("/api/rec")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/records")

    public List<MedicalRecord> getMedicalRecords(){

        return medicalRecordRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/records")
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }
    @GetMapping(value = "/records/{patient_id}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecordByPatientId(@PathVariable Long patient_id) throws AttributeNotFoundException {
        MedicalRecord medicalRecord = medicalRecordRepository.findByPatientId(patient_id);
        if (medicalRecord != null) {
            medicalRecordRepository.delete(medicalRecord);
        }

        medicalRecord.getPatient().getId();

        MedicalRecordDTO dto = new MedicalRecordDTO(medicalRecord);
        return ResponseEntity.ok(dto);

    }


    @PutMapping("/records/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecord medicalRecordDetails) throws AttributeNotFoundException {

        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("ABCD" + id));

        medicalRecord.setPatient(medicalRecordDetails.getPatient());
        medicalRecord.setCreatedAt(medicalRecordDetails.getCreatedAt());
        medicalRecord.setAnamnesis(medicalRecordDetails.getAnamnesis());
        medicalRecord.setComplaintsHistory(medicalRecordDetails.getComplaintsHistory());
        medicalRecord.setHospitalizationsHistory(medicalRecordDetails.getHospitalizationsHistory());
        medicalRecord.setId(medicalRecordDetails.getId());

        MedicalRecord updatedMedicalRecord = medicalRecordRepository.save(medicalRecord);
        return ResponseEntity.ok(updatedMedicalRecord);
    }

    @DeleteMapping("/records/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteMedicalRecord(@PathVariable Long id) throws AttributeNotFoundException{

        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("ABCD" + id));

        medicalRecordRepository.delete(medicalRecord);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
