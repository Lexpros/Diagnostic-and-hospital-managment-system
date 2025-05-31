
import {Component, Input, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Appointment} from '../appointment';
import {AppointmentService} from '../appointment.service';
import {Patient} from '../patient';
import {MedicalStaffService} from '../medical-staff.service';
import { MedicalStaff } from '../medical-staff';
@Component({
  selector: 'app-admin-creat-appointment',
  templateUrl: './admin-creat-appointment.component.html',
  styleUrls: ['./admin-creat-appointment.component.css']
})
export class AdminCreatAppointmentComponent implements OnInit {
 @Input() patient: Patient;
  appointments: Appointment[] = [];
  paginatedAppointments: Appointment[] = [];
  pageSize = 5;
  currentPage = 1;

  appointmentForm!: FormGroup;
  timeSlots: string[] = [];

  newAppointment: Appointment = new Appointment();
  listMedicalStaff: MedicalStaff[] = []; // Список медицинского персонала
  constructor(
    private appointmentService: AppointmentService,
    private medicalStaffService: MedicalStaffService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loadAppointments();
    this.loadMedicalStaff();
    this.initForm();
    this.generateTimeSlots();
  }

initForm(): void {
    this.appointmentForm = this.fb.group({
      date: ['', Validators.required],
      time: ['', Validators.required],
      purpose: ['', Validators.required],
      diagnosis: [''],
      doctor: [null, Validators.required],
      treatmentPlan: [''],
      conclusion: ['']
    });
  }

 generateTimeSlots(): void {
    const startHour = 8;
    const endHour = 20;
    const stepMinutes = 30;

    for (let hour = startHour; hour < endHour; hour++) {
      for (let min = 0; min < 60; min += stepMinutes) {
        const h = hour.toString().padStart(2, '0');
        const m = min.toString().padStart(2, '0');
        this.timeSlots.push(`${h}:${m}`);
      }
    }
  }

  loadAppointments(): void {
    if (this.patient.id) {
      this.appointmentService.getAppointmentsByPatientId(this.patient.id).subscribe(data => {
        this.appointments = data;
        this.updatePagination();
      });
    }
  }
  loadMedicalStaff(): void {
    // Здесь можно загрузить список медицинского персонала, если это необходимо
    // Например, через сервис MedicalStaffService
    this.medicalStaffService.getMedicalStaffList().subscribe(data => {
      this.listMedicalStaff = data;
    });

  }

  updatePagination(): void {
    const start = (this.currentPage - 1) * this.pageSize;
    this.paginatedAppointments = this.appointments.slice(start, start + this.pageSize);
  }

  nextPage() {
    if (this.currentPage * this.pageSize < this.appointments.length) {
      this.currentPage++;
      this.updatePagination();
    }
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePagination();
    }
  }

  onSubmit(): void {
    if (this.appointmentForm.invalid) return;

    const formValues = this.appointmentForm.value;
    const appointment = new Appointment();

    const dateStr = formValues.date;  // e.g. "2025-06-01"
    const timeStr = formValues.time;  // e.g. "14:30"
    const combinedDateTime = new Date(`${dateStr}T${timeStr}`);

    appointment.date = combinedDateTime;
    appointment.purpose = formValues.purpose;
    appointment.diagnosis = formValues.diagnosis;
    appointment.doctor = formValues.doctor;
    appointment.treatmentPlan = formValues.treatmentPlan;
    appointment.conclusion = formValues.conclusion;
    appointment.status = "Запланирован";
    appointment.patient = this.patient;

    this.appointmentService.createAppointment(appointment).subscribe(result => {
      this.appointments.push(result);
      this.updatePagination();
      this.appointmentForm.reset();
    });
  }
}

