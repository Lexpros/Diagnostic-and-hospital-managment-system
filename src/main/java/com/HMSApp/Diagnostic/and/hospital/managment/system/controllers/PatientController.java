package com.HMSApp.Diagnostic.and.hospital.managment.system.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.MedicalRecord;
import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.PatientRepository;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.MedicalRecord;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.MedicalRecordRepository;
import com.HMSApp.Diagnostic.and.hospital.managment.system.controllers.MedicalRecordController;

import javax.management.AttributeNotFoundException;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.MedicalRecordRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RequestMapping("/api/v1")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/patients")

    public List<Patient> getAllPatients(){

        return patientRepository.findAll();
    }

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/records")

    public List<MedicalRecord> getMedicalRecords(){

        return medicalRecordRepository.findAll();
    }

//    public PatientController(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws AttributeNotFoundException {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("ABCD" + id));

        return ResponseEntity.ok(patient);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) throws AttributeNotFoundException{

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("ABCD" + id));

        patient.setLastName(patientDetails.getLastName());
        patient.setFirstName(patientDetails.getFirstName());
        patient.setMiddleName(patientDetails.getMiddleName());
        patient.setBirthDate(patientDetails.getBirthDate());
        patient.setContactInfo(patientDetails.getContactInfo());
        patient.setId(patientDetails.getId());

        Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable Long id) {

        // Поиск пациента
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Пациент с id " + id + " не найден"));
        patientRepository.delete(patient);

        // Ответ клиенту
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
