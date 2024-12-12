import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import { PropiedadService } from '../../services/propiedad.service';

@Component({
  selector: 'app-propiedades',
  standalone: true,
  imports: [CommonModule, NgOptimizedImage],
  templateUrl: './propiedades.component.html',
  styleUrls: ['./propiedades.component.scss'],
})
export class PropiedadesComponent implements OnInit {
  propiedades: any[] = [];

  constructor(
    private route: ActivatedRoute,
    private propiedadService: PropiedadService
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      const ciudad = params['ciudad'];
      const provincia = params['provincia'];

      this.propiedadService.buscarPropiedades(ciudad, provincia).subscribe(
        (data) => (this.propiedades = data)
      );
    });
  }
}
