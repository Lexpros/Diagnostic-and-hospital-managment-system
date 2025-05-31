import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCreatAppointmentComponent } from './admin-creat-appointment.component';

describe('AdminCreatAppointmentComponent', () => {
  let component: AdminCreatAppointmentComponent;
  let fixture: ComponentFixture<AdminCreatAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminCreatAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminCreatAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
