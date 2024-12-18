import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private usuarioAutenticado: any = null;

  constructor(private http: HttpClient) {}

  guardarUsuarioEnLocalStorage(username: string): void {
    localStorage.setItem('usuario', JSON.stringify({ username }));
  }

  isAuthenticated(): boolean {
    return localStorage.getItem('usuario') !== null;
  }

  getUsername(): string | null {
    const usuario = localStorage.getItem('usuario');
    return usuario ? JSON.parse(usuario).username : null;
  }

  logout(): void {
    this.usuarioAutenticado = null;
    localStorage.removeItem('usuario');
    document.cookie = 'JSESSIONID=;expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
  }
}
