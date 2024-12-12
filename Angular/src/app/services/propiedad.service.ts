import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PropiedadService {
  private apiUrl = 'http://localhost:8080/api/propiedades';

  constructor(private http: HttpClient) {}

  buscarPropiedades(ciudad?: string, provincia?: string): Observable<any[]> {
    const params: any = {};
    if (ciudad) params.ciudad = ciudad;
    if (provincia) params.provincia = provincia;

    return this.http.get<any[]>(this.apiUrl, { params });
  }
}
