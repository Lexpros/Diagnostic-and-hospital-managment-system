import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../patient.service';
import { Patient } from '../patient';
@Component({
  selector: 'app-creat-medical-record',
  templateUrl: './creat-medical-record.component.html',
  styleUrls: ['./creat-medical-record.component.css']
})
export class CreatMedicalRecordComponent implements OnInit {

  id: number;
  patient: Patient;
  constructor(private route: ActivatedRoute, private patientService: PatientService) { }

  ngOnInit(): void {

    this.id = this.route.snapshot.params['id'];
    this.patient = new Patient();
    this.patientService.getPatientById(this.id).subscribe(data => { 
      this.patient = data;
    } );
  }

}
