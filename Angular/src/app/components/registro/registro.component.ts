import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-registro',
  standalone: true,
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.scss'],
  imports: [FormsModule, CommonModule],
})
export class RegistroComponent {
  usuario = {
    nombre: '',
    apellidos: '',
    dni: '',
    telefono: '',
    direccion: '',
    email: '',
    username: '',
    password: ''
  };

  constructor(
    public http: HttpClient,
    public router: Router
  ) {}

  registrarUsuario(): void {
    this.http.post('http://localhost:8080/api/usuario/signup', this.usuario).subscribe({
      next: () => {
        alert('Usuario registrado correctamente.');
        this.router.navigate(['/login']); // Redirige al login
      },
      error: (err) => {
        console.error('Error al registrar usuario:', err);
        alert('Error al registrar el usuario. Inténtelo de nuevo.');
      }
    });
  }
}
