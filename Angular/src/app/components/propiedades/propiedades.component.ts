import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CurrencyPipe, NgForOf, NgIf, NgOptimizedImage } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

interface Filtros {
  tipoContrato: string;
  tipoPropiedad: string;
  ciudad: string;
  provincia: string;
  pais: string;
  codigoPostal: string;
}

@Component({
  selector: 'app-propiedades',
  templateUrl: './propiedades.component.html',
  styleUrls: ['./propiedades.component.scss'],
  standalone: true,
  imports: [
    FormsModule,
    CurrencyPipe,
    RouterLink,
    NgOptimizedImage,
    NgIf,
    NgForOf
  ]
})
export class PropiedadesComponent implements OnInit {
  propiedades: any[] = [];
  menuVisible = false;

  filtros: Filtros = {
    tipoContrato: '',
    tipoPropiedad: '',
    ciudad: '',
    provincia: '',
    pais: '',
    codigoPostal: '',
  };

  ciudades: string[] = [];
  provincias: string[] = [];
  paises: string[] = [];
  codigosPostales: any[] = [];

  constructor(
    public http: HttpClient,
    public authService: AuthService,
    public router: Router,
  ) {}

  ngOnInit(): void {
    this.cargarUbicaciones();
    this.cargarPropiedades();
  }

  cargarPropiedades(): void {
    const params: { [key in keyof Filtros]?: string } = {};
    for (const key in this.filtros) {
      const filtroKey = key as keyof Filtros;
      if (this.filtros[filtroKey]) {
        params[filtroKey] = this.filtros[filtroKey];
      }
    }

    this.http.get<any[]>('http://localhost:8080/api/propiedades/filtrar', { params }).subscribe({
      next: (data) => (this.propiedades = data),
      error: () => alert('Error al cargar propiedades'),
    });
  }

  cargarUbicaciones(): void {
    this.http.get<any[]>('http://localhost:8080/api/ubicaciones').subscribe({
      next: (data) => {
        this.ciudades = [...new Set(data.map((u) => u.ciudad))];
        this.provincias = [...new Set(data.map((u) => u.provincia))];
        this.paises = [...new Set(data.map((u) => u.pais))];
        this.codigosPostales = data;
      },
    });
  }

  crearAnuncio(): void {
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/propiedades/new']);
    } else {
      alert('Debes iniciar sesión para crear un anuncio.');
      this.router.navigate(['/login']);
    }
  }

  aplicarFiltros(): void {
    this.cargarPropiedades();
  }

  setTipoContrato(tipo: string): void {
    this.filtros.tipoContrato = tipo;
    this.aplicarFiltros();
  }

  toggleMenu(): void {
    this.menuVisible = !this.menuVisible;
  }

  logout(): void {
    this.authService.logout();
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }
}
