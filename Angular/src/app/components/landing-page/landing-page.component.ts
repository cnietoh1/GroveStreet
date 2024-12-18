import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule, NgOptimizedImage } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink, NgOptimizedImage],
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss'],
})
export class LandingPageComponent implements OnInit {
  ciudad: string = '';
  provincia: string = '';
  menuVisible: boolean = false;
  username: string | null = null;

  constructor(
    public router: Router,
    public authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('username'); // Recupera el nombre de usuario
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  getUsername(): string | null {
    return this.authService.getUsername();
  }

  buscar(): void {
    // Navega a propiedades con filtros de ciudad y provincia
    this.router.navigate(['/propiedades'], {
      queryParams: { ciudad: this.ciudad, provincia: this.provincia },
    });
  }

  crearAnuncio(): void {
    // Comprueba si el usuario está autenticado antes de crear un anuncio
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['/propiedades/new']);
    } else {
      alert('Debes iniciar sesión para crear un anuncio.');
      this.router.navigate(['/login']);
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  toggleMenu(): void {
    // Alterna la visibilidad del menú
    this.menuVisible = !this.menuVisible;
  }
}
