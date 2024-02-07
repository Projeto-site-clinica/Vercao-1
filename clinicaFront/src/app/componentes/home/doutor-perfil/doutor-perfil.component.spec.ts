import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoutorPerfilComponent } from './doutor-perfil.component';

describe('DoutorPerfilComponent', () => {
  let component: DoutorPerfilComponent;
  let fixture: ComponentFixture<DoutorPerfilComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DoutorPerfilComponent]
    });
    fixture = TestBed.createComponent(DoutorPerfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
