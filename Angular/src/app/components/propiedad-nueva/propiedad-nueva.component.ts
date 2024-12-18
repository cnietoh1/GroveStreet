import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute, RouterLink } from '@angular/router';
import { NgForOf, NgIf, NgOptimizedImage } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-propiedad-nueva',
  standalone: true,
  templateUrl: './propiedad-nueva.component.html',
  styleUrls: ['./propiedad-nueva.component.scss'],
  imports: [NgIf, RouterLink, NgOptimizedImage, FormsModule, NgForOf],
})
export class PropiedadNuevaComponent implements OnInit {
  menuVisible: boolean = false;
  esModoEdicion: boolean = false; // Determina si es edición o creación
  propiedadId: number | null = null;

  propiedad: any = {
    titulo: '',
    descripcion: '',
    direccion: '',
    precio: '',
    habitaciones: '',
    banos: '',
    metrosCuadrados: '',
    tipoPropiedad: '',
    tipoContrato: '',
    ciudad: '',
    provincia: '',
    pais: '',
    codigoPostal: '',
    usuario: { id: null },
    ubicacion: {
      ciudad: '',
      provincia: '',
      pais: '',
      codigoPostal: '',
    },
    imagen: null, // Almacenará la imagen seleccionada
  };

  ciudades: string[] = [];
  provincias: string[] = [];
  paises: string[] = [];
  codigosPostales: any[] = [];

  constructor(
    public http: HttpClient,
    public authService: AuthService, // Uso de AuthService
    public router: Router, // Cambiado a public para el template
    public route: ActivatedRoute
  ) {}

  ngOnInit() {
    // Verificar autenticación
    if (!this.authService.isAuthenticated()) {
      alert('Por favor, inicia sesión para continuar.');
      this.router.navigate(['/login']);
      return;
    }

    // Obtener usuario autenticado
    this.obtenerUsuario();

    // Cargar ubicaciones
    this.cargarUbicaciones();

    // Revisar si es modo edición
    this.route.paramMap.subscribe((params) => {
      const id = params.get('id');
      if (id) {
        this.esModoEdicion = true;
        this.propiedadId = +id;
        this.cargarPropiedad(this.propiedadId);
      }
    });
  }

  obtenerUsuario() {
    const username = this.authService.getUsername();
    if (username) {
      this.http.get<any>(`http://localhost:8080/api/usuario/${username}`).subscribe({
        next: (data) => {
          this.propiedad.usuario.id = data.id;
        },
        error: () => {
          alert('Error al obtener datos del usuario.');
          this.router.navigate(['/login']);
        },
      });
    } else {
      alert('No se pudo obtener el usuario autenticado.');
      this.router.navigate(['/login']);
    }
  }

  cargarUbicaciones() {
    this.http.get<any[]>('http://localhost:8080/api/ubicaciones').subscribe({
      next: (data) => {
        // Asume que `data` tiene la estructura [{ codigoPostal: '05001', ciudad: 'Ávila' }, ...]
        this.ciudades = [...new Set(data.map((ubicacion) => ubicacion.ciudad))].filter(Boolean);
        this.provincias = [...new Set(data.map((ubicacion) => ubicacion.provincia))].filter(Boolean);
        this.paises = [...new Set(data.map((ubicacion) => ubicacion.pais))].filter(Boolean);

        // Corregir la lista de códigos postales
        this.codigosPostales = data.map((ubicacion) => ({
          codigoPostal: ubicacion.codigoPostal,
          ciudad: ubicacion.ciudad
        }));
      },
      error: (error) => {
        console.error('Error al cargar datos de ubicación:', error);
        alert('Error al cargar datos de ubicación.');
      },
    });
  }

  cargarPropiedad(id: number) {
    this.http.get<any>(`http://localhost:8080/api/propiedades/${id}`).subscribe({
      next: (data) => {
        this.propiedad = data; // Rellena el formulario con los datos existentes
      },
      error: () => {
        alert('Error al cargar los datos de la propiedad.');
      },
    });
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.propiedad.imagen = file; // Asigna el archivo seleccionado
    }
  }

  guardarPropiedad() {
    const endpoint = this.esModoEdicion
      ? `http://localhost:8080/api/propiedades/update/${this.propiedadId}`
      : 'http://localhost:8080/api/propiedades/new';

    const formData = new FormData();

    // Agregar datos básicos
    Object.keys(this.propiedad).forEach((key) => {
      if (key !== 'imagen' && key !== 'ubicacion') {
        formData.append(key, this.propiedad[key]);
      }
    });

    formData.append('ciudad', this.propiedad.ubicacion.ciudad);
    formData.append('provincia', this.propiedad.ubicacion.provincia);
    formData.append('pais', this.propiedad.ubicacion.pais);
    formData.append('codigoPostal', this.propiedad.ubicacion.codigoPostal);

    // Adjuntar imagen si existe
    if (this.propiedad.imagen) {
      formData.append('imagen', this.propiedad.imagen);
    }

    this.http.post<any>(endpoint, formData).subscribe({
      next: () => {
        alert(`Propiedad ${this.esModoEdicion ? 'actualizada' : 'creada'} correctamente.`);
        this.router.navigate(['/propiedades']);
      },
      error: (error) => {
        console.error('Error al guardar la propiedad:', error);
        alert(`Error al ${this.esModoEdicion ? 'actualizar' : 'guardar'} la propiedad.`);
      },
    });
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  getUsername(): string | null {
    return this.authService.getUsername();
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  toggleMenu(): void {
    this.menuVisible = !this.menuVisible;
  }
}
