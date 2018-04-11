import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterCinemaComponent } from './register-cinema.component';

describe('RegisterCinemaComponent', () => {
  let component: RegisterCinemaComponent;
  let fixture: ComponentFixture<RegisterCinemaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterCinemaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterCinemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
