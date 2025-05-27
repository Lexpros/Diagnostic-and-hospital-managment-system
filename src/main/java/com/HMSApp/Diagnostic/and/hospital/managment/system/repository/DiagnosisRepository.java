package com.HMSApp.Diagnostic.and.hospital.managment.system.repository;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    public void deleteById(Long id);
}
