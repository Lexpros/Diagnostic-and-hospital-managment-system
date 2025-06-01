import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import {Appointment} from '../appointment';
import {AppointmentService} from '../appointment.service';
import {MedicalStaff} from '../medical-staff';
import {MedicalStaffService} from '../medical-staff.service';
import {Patient} from '../patient';

@Component({
  selector: 'app-admin-creat-appointment',
  templateUrl: './admin-creat-appointment.component.html',
  styleUrls: ['./admin-creat-appointment.component.css']
})
export class AdminCreatAppointmentComponent implements OnInit {
  @Input() patient: Patient;
  @Input() role: 'admin' | 'doctor';  
  appointments: Appointment[] = [];
  paginatedAppointments: Appointment[] = [];
  pageSize = 5;
  currentPage = 1;

  appointmentForm!: FormGroup;
  timeSlots: string[] = [];
  listMedicalStaff: MedicalStaff[] = [];

  creatingNew = false;
  editingMap: {[id: number]: boolean} = {};

  constructor(
      private appointmentService: AppointmentService,
      private medicalStaffService: MedicalStaffService,
      private fb: FormBuilder) {}
  

  isAdmin(): boolean {
    return this.role === 'admin';
  }

  isDoctor(): boolean {
    return this.role === 'doctor';
  }
  appointmentStatuses: string[] = ['Запланирован', 'Завершён', 'Отменён'];
  //Методы для формы
  compareDoctors(d1: any, d2: any): boolean {
    return d1 && d2 ? d1.id === d2.id : d1 === d2;
  }

  formatDateTimeLocal(date: string|Date): string {
    const d = new Date(date);
    const year = d.getFullYear();
    const month = ('0' + (d.getMonth() + 1)).slice(-2);
    const day = ('0' + d.getDate()).slice(-2);
    const hours = ('0' + d.getHours()).slice(-2);
    const minutes = ('0' + d.getMinutes()).slice(-2);
    return `${year}-${month}-${day}T${hours}:${minutes}`;
  }

  onDateTimeChange(event: Event, app: any): void {
    const input = event.target as HTMLInputElement;
    app.date = new Date(input.value);
  }

  //Методы для формы

  ngOnInit(): void {
    console.log('Роль:', this.role);
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
    for (let hour = 8; hour < 20; hour++) {
      for (let min of [0, 30]) {
        const h = hour.toString().padStart(2, '0');
        const m = min.toString().padStart(2, '0');
        this.timeSlots.push(`${h}:${m}`);
      }
    }
  }

  loadAppointments(): void {
    if (this.patient?.id) {
      this.appointmentService.getAppointmentsByPatientId(this.patient.id)
          .subscribe(data => {
            this.appointments = data;
            this.appointments.sort((a, b) => new Date(b.date).getTime() - new Date(a.date).getTime());
            this.updatePagination();
          });
    }
  }

  loadMedicalStaff(): void {
    this.medicalStaffService.getMedicalStaffList().subscribe(data => {
      this.listMedicalStaff = data;
    });
  }

  updatePagination(): void {
    const start = (this.currentPage - 1) * this.pageSize;
    this.paginatedAppointments =
        this.appointments.slice(start, start + this.pageSize);
  }

  nextPage(): void {
    if (this.currentPage * this.pageSize < this.appointments.length) {
      this.currentPage++;
      this.updatePagination();
    }
  }

  prevPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePagination();
    }
  }

  showCreateForm(): void {
    this.creatingNew = true;
    this.appointmentForm.reset();
  }

  cancelCreate(): void {
    this.creatingNew = false;
    this.appointmentForm.reset();
  }

  onSubmit(): void {
    if (this.appointmentForm.invalid) return;

    const formValues = this.appointmentForm.value;
    const appointment = new Appointment();
    const combinedDate = new Date(`${formValues.date}T${formValues.time}`);

    appointment.date = combinedDate;
    appointment.purpose = formValues.purpose;
    appointment.diagnosis = formValues.diagnosis;
    appointment.doctor = formValues.doctor;
    appointment.treatmentPlan = formValues.treatmentPlan;
    appointment.conclusion = formValues.conclusion;
    appointment.status = 'Запланирован';
    appointment.patient = this.patient;

    this.appointmentService.createAppointment(appointment).subscribe(result => {
      this.appointments.push(result);
      this.updatePagination();
      this.creatingNew = false;
    });
  }

  enableEdit(id: number): void {
    this.editingMap[id] = true;
  }

  saveEditedAppointment(app: Appointment): void {
    this.appointmentService.updateAppointment(app.id, app).subscribe(() => {
      this.editingMap[app.id] = false;
    });
  }

  cancelEdit(id: number): void {
    this.editingMap[id] = false;
    this.loadAppointments();  // чтобы откатить изменения
  }
}
