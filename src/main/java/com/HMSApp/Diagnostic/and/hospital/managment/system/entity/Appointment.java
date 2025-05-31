package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('appointments_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "doctor_id", nullable = false)
    private MedicalStaff doctor;

    @Column(name = "purpose", nullable = false, length = Integer.MAX_VALUE)
    private String purpose;

    @Column(name = "status")
    private String status;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "conclusion", length = Integer.MAX_VALUE)
    private String conclusion;

    @Column(name = "diagnosis", length = Integer.MAX_VALUE)
    private String diagnosis;
    @Column(name = "treatment_plan", length = Integer.MAX_VALUE)
    private String treatmentPlan;


}