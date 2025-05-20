package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "examinations")
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('examinations_exam_id_seq')")
    @Column(name = "exam_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "record_id", nullable = false)
    private MedicalRecord record;

    @Column(name = "exam_date", nullable = false)
    private LocalDate examDate;

    @Column(name = "disease_stage", length = 100)
    private String diseaseStage;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "staff_id")
    private MedicalStaff staff;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "facility_id")
    private MedicalFacility facility;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MedicalRecord getRecord() {
        return record;
    }

    public void setRecord(MedicalRecord record) {
        this.record = record;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public String getDiseaseStage() {
        return diseaseStage;
    }

    public void setDiseaseStage(String diseaseStage) {
        this.diseaseStage = diseaseStage;
    }

    public MedicalStaff getStaff() {
        return staff;
    }

    public void setStaff(MedicalStaff staff) {
        this.staff = staff;
    }

    public MedicalFacility getFacility() {
        return facility;
    }

    public void setFacility(MedicalFacility facility) {
        this.facility = facility;
    }

/*
 TODO [Reverse Engineering] create field to map the 'exam_type' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "exam_type", columnDefinition = "exam_type_enum not null")
    private Object examType;
*/
}