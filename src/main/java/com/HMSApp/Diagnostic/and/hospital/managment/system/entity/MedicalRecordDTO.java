package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDTO {
    private Long id;
    private String anamnesis;
    private String complaintsHistory;
    private String hospitalizationsHistory;
    private LocalDate createdAt;
    private Long patientId;

    public MedicalRecordDTO(MedicalRecord record) {
        this.id = record.getId();
        this.anamnesis = record.getAnamnesis();
        this.complaintsHistory = record.getComplaintsHistory();
        this.hospitalizationsHistory = record.getHospitalizationsHistory();
        this.createdAt = record.getCreatedAt();
        this.patientId = record.getPatient().getId();
    }
}
