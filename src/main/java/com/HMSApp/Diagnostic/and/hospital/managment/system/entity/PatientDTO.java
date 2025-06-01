package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PatientDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private Date birthDate;
    private String sex;
    private String contactInfo;
    // Добавь другие нужные поля
}