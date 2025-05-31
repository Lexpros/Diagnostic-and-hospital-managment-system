package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import lombok.Getter;
import lombok.Lombok;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {
    private Long id;
    private String purpose;
    private String date;
    private String status;
    private String patientLastName;
    private String doctorName;
}
