import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';
import { CommonModule, CurrencyPipe, NgForOf, NgIf, NgOptimizedImage } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-propiedades-list',
  standalone: true,
  templateUrl: './propiedades-list.component.html',
  styleUrls: ['./propiedades-list.component.scss'],
  imports: [NgIf, RouterLink, CurrencyPipe, NgForOf, NgOptimizedImage],
})
export class PropiedadesListComponent implements OnInit {
  propiedades: any[] = [];
  menuVisible: boolean = false;
  username: string | null = null;

  constructor(
      public http: HttpClient,
      public router: Router,
      public authService: AuthService
  ) {}

  ngOnInit(): void {
    // Verificar autenticación
    if (!this.authService.isAuthenticated()) {
      alert('Debes iniciar sesión para ver tus anuncios.');
      this.router.navigate(['/login']);
      return;
    }

    // Obtener el nombre de usuario autenticado
    this.username = this.authService.getUsername();
    if (this.username) {
      this.cargarPropiedadesUsuario();
    } else {
      alert('No se pudo obtener el usuario autenticado.');
      this.router.navigate(['/login']);
    }
  }

  cargarPropiedadesUsuario(): void {
    // Obtener propiedades del usuario autenticado
    this.http
        .get<any[]>(
            `http://localhost:8080/api/propiedades/list/usuario=${this.username}`,
            { withCredentials: true } // Envía las cookies de sesión
        )
        .subscribe({
          next: (data) => {
            this.propiedades = data;
            console.log('Propiedades cargadas:', data);
          },
          error: (err) => {
            console.error('Error al cargar propiedades:', err);
            alert('Error al cargar tus anuncios. Intenta nuevamente.');
            this.router.navigate(['/login']);
          },
        });
  }

  crearAnuncio(): void {
    this.router.navigate(['/propiedades/new']);
  }

  toggleMenu(): void {
    this.menuVisible = !this.menuVisible;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  getUsername(): string | null {
    return this.authService.getUsername();
  }
}
