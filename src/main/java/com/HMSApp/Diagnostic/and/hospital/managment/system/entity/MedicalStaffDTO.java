package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalStaffDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String specialty;
    private String contactInfo;
    private String login;

    public MedicalStaffDTO() {}

    public MedicalStaffDTO(Long id, String lastName, String firstName, String middleName,
                           String specialty, String contactInfo, String login) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.specialty = specialty;
        this.contactInfo = contactInfo;
        this.login = login;
    }
}

