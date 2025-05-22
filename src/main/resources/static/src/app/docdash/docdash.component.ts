import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from '../patient';
import { PatientService } from '../patient.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CreatMedicalRecordComponent } from '../creat-medical-record/creat-medical-record.component';

@Component({
  selector: 'app-docdash',
  templateUrl: './docdash.component.html',
  styleUrls: ['./docdash.component.css']
})
export class DocdashComponent implements OnInit {
  searchText: string = '';
  patients: Patient[] = [];

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
    });
  }

  openMedicalRecordModal(patient: Patient) {
    const modalRef = this.modalService.open(CreatMedicalRecordComponent, {
      size: 'lg',
      backdrop: 'static'
    });
    modalRef.componentInstance.patient = patient;
  }

  viewPatient(id: number) {
    this.router.navigate(['viewpatient', id]);
  }

  updatePatient(id: number) {
    this.router.navigate(['updatepatient', id]);
  }

  deletePatient(id: number) {
    this.patientService.deletePatient(id).subscribe(() => {
      this.getPatients();
    });
  }  
}
