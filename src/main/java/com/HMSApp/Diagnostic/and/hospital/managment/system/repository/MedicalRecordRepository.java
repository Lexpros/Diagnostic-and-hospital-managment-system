package com.HMSApp.Diagnostic.and.hospital.managment.system.repository;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.MedicalRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository <MedicalRecord, Long> {
    public void deleteById(Long id);
    MedicalRecord findByPatientId(Long patientId);
}
