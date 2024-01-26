import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoutorDetalhesComponent } from './doutor-detalhes.component';

describe('DoutorDetalhesComponent', () => {
  let component: DoutorDetalhesComponent;
  let fixture: ComponentFixture<DoutorDetalhesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DoutorDetalhesComponent]
    });
    fixture = TestBed.createComponent(DoutorDetalhesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
