import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Patient } from '../patient';
import { PatientService } from '../patient.service';
import { CreatMedicalRecordComponent } from '../creat-medical-record/creat-medical-record.component';

@Component({
  selector: 'app-admindash',
  templateUrl: './admindash.component.html',
  styleUrls: ['./admindash.component.css']
})
export class AdmindashComponent implements OnInit {

  searchText: string;
  patients: Patient[]; 

  constructor(
    private patientService: PatientService,
    private router: Router,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {

    this.getPatients();
  }

  private getPatients() {
    this.patientService.getPatientslist().subscribe(data => {
      this.patients = data;
      this.patients.sort((a, b) => b.id - a.id);
    });
  }

  viewPatient(id: number) {

    this.router.navigate(['viewpatient', id]);

  }
  
  updatePatient(id: number) {

    this.router.navigate(['updatepatient', id]);

  }

  deletePatient(id: number) {
  this.patientService.deletePatient(id).subscribe({
    next: (data) => {
      console.log('Удалено:', data);
      this.getPatients(); // перезапрашивает список
    },
    error: (err) => {
      console.error('Ошибка при удалении пациента:', err);
      alert('Не удалось удалить пациента');
    }
  });
}


  openMedicalRecordModal(patient: Patient) {
    const modalRef = this.modalService.open(CreatMedicalRecordComponent, {
      size: 'lg',
      backdrop: 'static'
    });
    modalRef.componentInstance.patient = patient;
    modalRef.componentInstance.mode = 'admin'; // передаём режим
  }

}
