import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropiedadNuevaComponent } from './propiedad-nueva.component';

describe('PropiedadNuevaComponent', () => {
  let component: PropiedadNuevaComponent;
  let fixture: ComponentFixture<PropiedadNuevaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PropiedadNuevaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropiedadNuevaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
