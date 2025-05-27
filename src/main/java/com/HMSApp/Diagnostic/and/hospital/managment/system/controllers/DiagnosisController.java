package com.HMSApp.Diagnostic.and.hospital.managment.system.controllers;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Diagnosis;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/diagnoses")
public class DiagnosisController {

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @GetMapping
    public List<Diagnosis> getAllDiagnoses() {
        return diagnosisRepository.findAll();
    }

    @PostMapping
    public Diagnosis createDiagnosis(@RequestBody Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnosis> getDiagnosisById(@PathVariable Long id) throws AttributeNotFoundException {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Диагноз с id " + id + " не найден"));
        return ResponseEntity.ok(diagnosis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diagnosis> updateDiagnosis(@PathVariable Long id, @RequestBody Diagnosis newDetails)
            throws AttributeNotFoundException {

        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Диагноз с id " + id + " не найден"));

        diagnosis.setExam(newDetails.getExam());
        diagnosis.setDiagnosisCode(newDetails.getDiagnosisCode());
        diagnosis.setDiagnosisName(newDetails.getDiagnosisName());
        diagnosis.setAnalysisDate(newDetails.getAnalysisDate());
        diagnosis.setConfirmedAt(newDetails.getConfirmedAt());

        Diagnosis updatedDiagnosis = diagnosisRepository.save(diagnosis);
        return ResponseEntity.ok(updatedDiagnosis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDiagnosis(@PathVariable Long id)
            throws AttributeNotFoundException {

        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Диагноз с id " + id + " не найден"));

        diagnosisRepository.delete(diagnosis);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
