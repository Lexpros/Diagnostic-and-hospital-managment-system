package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {
    private Long id;
    private PatientDTO patient;
    private MedicalStaffDTO doctor;
    private String purpose;
    private String date;
    private String conclusion;
    private String status;
    private String diagnosis;
    private String treatmentPlan;
}

