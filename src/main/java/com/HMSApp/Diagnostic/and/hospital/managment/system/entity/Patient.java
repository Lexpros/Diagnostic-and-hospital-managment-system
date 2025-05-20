package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "birth_date")
    private Date dateOfBirth;
    @Column(name = "sex")
    private String sex;
    @Column(name = "contact_info")
    private String contactInfo;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
