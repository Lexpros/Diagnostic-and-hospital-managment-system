import { MedicalStaff } from './medical-staff';
import { Patient } from './patient';
export class Appointment {
    id: number;
    patient: Patient;
    doctor: MedicalStaff;
    purpose: string;
    date: Date;
    conclusion?: string = ""; // Заключение врача, может быть необязательным
    status: string; // Статус приема, например "Запланирован", "Завершен", "Отменен"
    diagnosis?: string = ""; // Диагноз, может быть необязательным
    treatmentPlan?: string = ""; // План лечения, может быть необязательным    
}
