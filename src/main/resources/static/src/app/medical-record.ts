import { Patient } from './patient';
export class MedicalRecord {
  id: number;                     // уникальный идентификатор медицинской карты
  patient: Patient;                     // ссылка на объект пациента
  createdAt!: Date;                      // дата создания записи
  anamnesis?: string = "";                    // анамнез (может быть необязательным)
  complaintsHistory?: string = "";           // история жалоб
  hospitalizationsHistory?: string = "";     // история госпитализаций
}

