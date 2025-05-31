package com.HMSApp.Diagnostic.and.hospital.managment.system.repository;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Appointment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findByPatientId(Long patientId);
}
