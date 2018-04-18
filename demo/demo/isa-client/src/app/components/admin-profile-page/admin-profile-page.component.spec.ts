import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProfilePageComponent } from './admin-profile-page.component';

describe('AdminProfilePageComponent', () => {
  let component: AdminProfilePageComponent;
  let fixture: ComponentFixture<AdminProfilePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminProfilePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProfilePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
