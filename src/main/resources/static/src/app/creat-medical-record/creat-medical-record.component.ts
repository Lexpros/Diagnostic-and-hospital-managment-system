import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { MedicalRecord } from '../medical-record';
import { MedicalRecordService } from '../medical-record.service';
import { Patient } from '../patient';

@Component({
  selector: 'app-creat-medical-record',
  templateUrl: './creat-medical-record.component.html',
  styleUrls: ['./creat-medical-record.component.css']
})
export class CreatMedicalRecordComponent implements OnInit {

  @Input() patient: Patient; // Получаем пациента из родительского компонента (через модалку)

  newRecord: MedicalRecord = new MedicalRecord();

  constructor(
    private medicalRecordService: MedicalRecordService,
    public activeModal: NgbActiveModal
  ) {}

  ngOnInit(): void {
    // patient передаётся через @Input() — дополнительных загрузок не требуется
  }

  save(): void {
    this.newRecord.patient = this.patient;

    this.medicalRecordService.createMedicalRecord(this.newRecord).subscribe({
      next: (response) => {
        console.log('Медицинская карта сохранена:', response);
        this.activeModal.close('saved');
      },
      error: (err) => {
        console.error('Ошибка при сохранении:', err);
      }
    });
  }

  cancel(): void {
    this.activeModal.dismiss('cancel');
  }

  onSubmit() {
    console.log(this.patient);
    this.save();
  }
}
