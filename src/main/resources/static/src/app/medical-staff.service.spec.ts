import { TestBed } from '@angular/core/testing';

import { MedicalStaffService } from './medical-staff.service';

describe('MedicalStaffService', () => {
  let service: MedicalStaffService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalStaffService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
