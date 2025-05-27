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
  this.medicalRecordService.getMedicalRecordsByPatientId(this.patient.id).subscribe({
    next: (record) => {
      if (record) {
        this.newRecord = record; // Заполняем форму существующей записью
        console.log('Медицинская карта найдена:', record);
      }
    },
    error: (err) => {
      console.warn('Медицинская карта не найдена, создаём новую');
    }
  });
}

  save(): void {
  this.newRecord.patient = this.patient;

  if (this.newRecord.id) {
    // Обновление существующей записи
    this.medicalRecordService.updateMedicalRecord(this.newRecord.id, this.newRecord).subscribe({
      next: (response) => {
        console.log('Медицинская карта обновлена:', response);
        this.activeModal.close('updated');
      },
      error: (err) => {
        console.error('Ошибка при обновлении:', err);
      }
    });
  } else {
    // Создание новой записи
    this.medicalRecordService.createMedicalRecord(this.newRecord).subscribe({
      next: (response) => {
        console.log('Медицинская карта создана:', response);
        this.activeModal.close('created');
      },
      error: (err) => {
        console.error('Ошибка при создании:', err);
      }
    });
  }
}


  cancel(): void {
    this.activeModal.dismiss('cancel');
  }

  onSubmit() {
    console.log(this.patient);
    this.save();
  }
}
