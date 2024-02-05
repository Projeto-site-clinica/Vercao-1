import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaDetalhesComponent } from './consulta-detalhes.component';

describe('DoutorDetalhesComponent', () => {
  let component: ConsultaDetalhesComponent;
  let fixture: ComponentFixture<ConsultaDetalhesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsultaDetalhesComponent]
    });
    fixture = TestBed.createComponent(ConsultaDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
