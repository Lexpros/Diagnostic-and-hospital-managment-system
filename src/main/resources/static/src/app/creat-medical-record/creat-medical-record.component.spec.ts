import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CreatMedicalRecordComponent } from './creat-medical-record.component';

describe('CreatMedicalRecordComponent', () => {
  let component: CreatMedicalRecordComponent;
  let fixture: ComponentFixture<CreatMedicalRecordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatMedicalRecordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatMedicalRecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
