package com.HMSApp.Diagnostic.and.hospital.managment.system.repository;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long>{
    public void deleteById(Long id);
}
