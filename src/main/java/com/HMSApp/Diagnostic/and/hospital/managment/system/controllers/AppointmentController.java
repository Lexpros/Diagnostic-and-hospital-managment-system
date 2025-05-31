package com.HMSApp.Diagnostic.and.hospital.managment.system.controllers;

import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.Appointment;
import com.HMSApp.Diagnostic.and.hospital.managment.system.entity.AppointmentDTO;
import com.HMSApp.Diagnostic.and.hospital.managment.system.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/api/appoint")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/appointments")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @GetMapping("/appointments/{patient_id}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable Long patient_id) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patient_id);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails) throws AttributeNotFoundException {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Appointment not found for ID: " + id));

        appointment.setPatient(appointmentDetails.getPatient());
        appointment.setDoctor(appointmentDetails.getDoctor());
        appointment.setPurpose(appointmentDetails.getPurpose());
        appointment.setStatus(appointmentDetails.getStatus());
        appointment.setDate(appointmentDetails.getDate());
        appointment.setConclusion(appointmentDetails.getConclusion());
        appointment.setDiagnosis(appointmentDetails.getDiagnosis());
        appointment.setTreatmentPlan(appointmentDetails.getTreatmentPlan());

        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAppointment(@PathVariable Long id) throws AttributeNotFoundException {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Appointment not found for ID: " + id));

        appointmentRepository.delete(appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
