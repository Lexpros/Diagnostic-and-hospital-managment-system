package com.HMSApp.Diagnostic.and.hospital.managment.system.controllers;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Treatment;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/treatment")
public class TreatmentController {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @GetMapping("/all")
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    @PostMapping("/create")
    public Treatment createTreatment(@RequestBody Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) throws AttributeNotFoundException {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Лечение с id " + id + " не найдено"));
        return ResponseEntity.ok(treatment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatmentDetails)
            throws AttributeNotFoundException {

        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Лечение с id " + id + " не найдено"));

        treatment.setDiagnosis(treatmentDetails.getDiagnosis());
        treatment.setTreatmentType(treatmentDetails.getTreatmentType());
        treatment.setTreatmentName(treatmentDetails.getTreatmentName());
        treatment.setDosage(treatmentDetails.getDosage());
        treatment.setStartDate(treatmentDetails.getStartDate());
        treatment.setEndDate(treatmentDetails.getEndDate());

        Treatment updatedTreatment = treatmentRepository.save(treatment);
        return ResponseEntity.ok(updatedTreatment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTreatment(@PathVariable Long id)
            throws AttributeNotFoundException {

        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Лечение с id " + id + " не найдено"));

        treatmentRepository.delete(treatment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
