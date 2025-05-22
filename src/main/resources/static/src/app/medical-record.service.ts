import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {MedicalRecord} from './medical-record';

@Injectable({providedIn: 'root'})
export class MedicalRecordService {
  private baseUrl = 'http://localhost:8080/api/rec/records';

  constructor(private httpClient: HttpClient) {}

  getMedicalRecordList(): Observable<MedicalRecord[]> {
    return this.httpClient.get<MedicalRecord[]>(`${this.baseUrl}`);
  }

  createMedicalRecord(record: MedicalRecord): Observable<MedicalRecord> {
    return this.httpClient.post<MedicalRecord>(`${this.baseUrl}`, record);
  }

  getMedicalRecordById(id: number): Observable<MedicalRecord> {
    return this.httpClient.get<MedicalRecord>(`${this.baseUrl}/${id}`);
  }

  updateMedicalRecord(id: number, record: MedicalRecord): Observable<Object> {
    return this.httpClient.put(`${this.baseUrl}/${id}`, record);
  }
}
