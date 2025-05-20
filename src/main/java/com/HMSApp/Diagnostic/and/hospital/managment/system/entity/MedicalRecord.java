package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "medicalrecords")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('medicalrecords_record_id_seq')")
    @Column(name = "record_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "anamnesis", length = Integer.MAX_VALUE)
    private String anamnesis;

    @Column(name = "complaints_history", length = Integer.MAX_VALUE)
    private String complaintsHistory;

    @Column(name = "hospitalizations_history", length = Integer.MAX_VALUE)
    private String hospitalizationsHistory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getComplaintsHistory() {
        return complaintsHistory;
    }

    public void setComplaintsHistory(String complaintsHistory) {
        this.complaintsHistory = complaintsHistory;
    }

    public String getHospitalizationsHistory() {
        return hospitalizationsHistory;
    }

    public void setHospitalizationsHistory(String hospitalizationsHistory) {
        this.hospitalizationsHistory = hospitalizationsHistory;
    }

}