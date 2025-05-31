import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import { MedicalStaff } from './medical-staff';

@Injectable({
  providedIn: 'root'
})
export class MedicalStaffService {
   private baseUrl = 'http://localhost:8080/api/staff/all';
  
   constructor(private httpClient: HttpClient) {}

   getMedicalStaffList(): Observable<MedicalStaff[]> {
    return this.httpClient.get<MedicalStaff[]>(`${this.baseUrl}`);
  }

  // createMedicalRecord(record: MedicalRecord): Observable<MedicalRecord> {
  //   return this.httpClient.post<MedicalRecord>(`${this.baseUrl}`, record);
  // }

  getMedicalStaffById(id: number): Observable<MedicalStaff> {
    return this.httpClient.get<MedicalStaff>(`${this.baseUrl}/${id}`);
  }

  updateMedicalStaffList(patientId: number, medicalStaff: MedicalStaff): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${patientId}`, medicalStaff);
  }  
}
