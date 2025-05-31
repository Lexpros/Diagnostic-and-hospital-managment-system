export class MedicalStaff {
  id: number; // соответствует staff_id
  lastName: string;
  firstName: string;
  middleName: string;
  role: 'ADMIN' | 'DOCTOR' | 'NURSE'; // предполагается, что role_enum ограничен этими значениями
  specialty?: string; // может быть null в БД, значит optional
  contactInfo?: string; // также может быть необязательным

  get fullName(): string {
    return `${this.lastName} ${this.firstName} ${this.middleName}`;
  }
}
