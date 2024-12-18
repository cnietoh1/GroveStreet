import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PropiedadesListComponent } from './propiedades-list.component';

describe('PropiedadesListComponent', () => {
  let component: PropiedadesListComponent;
  let fixture: ComponentFixture<PropiedadesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PropiedadesListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PropiedadesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
