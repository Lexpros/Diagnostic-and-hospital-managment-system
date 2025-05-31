package com.HMSApp.Diagnostic.and.hospital.managment.system.controllers;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.MedicalRecord;
import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.MedicalRecordDTO;
import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Patient;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.MedicalRecordRepository;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.PatientRepository;
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

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients(){

        return patientRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/records")
    public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @GetMapping(value = "/records/{patient_id}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecordByPatientId(@PathVariable Long patient_id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByPatientId(patient_id);
        if (medicalRecord == null) {
            return ResponseEntity.notFound().build(); // ← Возвращает 404, корректно обрабатываемый на клиенте
        }

        MedicalRecordDTO dto = new MedicalRecordDTO(medicalRecord);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/records/{patient_id}")
    public ResponseEntity<MedicalRecordDTO> updateMedicalRecord(
            @PathVariable Long patient_id,
            @RequestBody MedicalRecordDTO dto) {

        MedicalRecord medicalRecord = medicalRecordRepository.findByPatientId(patient_id);
        if (medicalRecord == null) {
            return ResponseEntity.notFound().build();
        }

        // Обновляем поля из DTO
        medicalRecord.setCreatedAt(dto.getCreatedAt());
        medicalRecord.setAnamnesis(dto.getAnamnesis());
        medicalRecord.setComplaintsHistory(dto.getComplaintsHistory());
        medicalRecord.setHospitalizationsHistory(dto.getHospitalizationsHistory());

        // Связь с пациентом — через patientId из DTO
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElse(null);

        if (patient == null) {
            return ResponseEntity.badRequest().body(null); // Невалидный patientId
        }

        medicalRecord.setPatient(patient);

        // Сохраняем и возвращаем как DTO
        MedicalRecord updated = medicalRecordRepository.save(medicalRecord);
        return ResponseEntity.ok(new MedicalRecordDTO(updated));
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
