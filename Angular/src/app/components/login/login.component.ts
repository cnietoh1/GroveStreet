import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import {Router, RouterLink} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  imports: [ FormsModule, RouterLink, NgIf ],
})
export class LoginComponent {
  credentials = { username: '', password: '' };
  errorMessage = '';

  constructor(
    public authService: AuthService,
    public router: Router,
    public http: HttpClient
  ) {}

  login(): void {
    this.http
      .post<{ username: string; message: string }>(
        'http://localhost:8080/api/usuario/login',
        this.credentials,
        { withCredentials: true }
      )
      .subscribe({
        next: (response) => {
          console.log('Login exitoso:', response.message);
          this.authService.guardarUsuarioEnLocalStorage(response.username); // Guarda el usuario
          this.router.navigate(['/propiedades']);
        },
        error: () => {
          this.errorMessage = 'Credenciales incorrectas. Inténtalo de nuevo.';
        },
      });
  }
}
