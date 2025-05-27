package com.HMSApp.Diagnostic.and.hospital.managment.system.controllers;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Examination;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/exams")
public class ExaminationController {

    @Autowired
    private ExaminationRepository examinationRepository;

    @GetMapping
    public List<Examination> getAllExaminations() {
        return examinationRepository.findAll();
    }

    @PostMapping
    public Examination createExamination(@RequestBody Examination examination) {
        return examinationRepository.save(examination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Examination> getExaminationById(@PathVariable Long id) throws AttributeNotFoundException {
        Examination exam = examinationRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Обследование с id " + id + " не найдено"));
        return ResponseEntity.ok(exam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Examination> updateExamination(@PathVariable Long id, @RequestBody Examination examDetails)
            throws AttributeNotFoundException {

        Examination exam = examinationRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Обследование с id " + id + " не найдено"));

        exam.setRecord(examDetails.getRecord());
        exam.setExamDate(examDetails.getExamDate());
        exam.setDiseaseStage(examDetails.getDiseaseStage());
        exam.setFacility(examDetails.getFacility());
        exam.setStaff(examDetails.getStaff());
        exam.setExamType(examDetails.getExamType());

        Examination updatedExam = examinationRepository.save(exam);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteExamination(@PathVariable Long id)
            throws AttributeNotFoundException {

        Examination exam = examinationRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Обследование с id " + id + " не найдено"));

        examinationRepository.delete(exam);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
