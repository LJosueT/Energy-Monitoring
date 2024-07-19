// datos.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DatosDto } from "../Dto/datos.dto";

@Injectable({
  providedIn: 'root'
})
export class DatosService {

  private apiUrl = 'http://localhost:8080/api'; // Ajusta esta URL según tu configuración

  constructor(private http: HttpClient) {}

  getCorriente(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/corriente`);
  }

  getPotencia(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/potencia`);
  }

  getDatos(): Observable<DatosDto> {
    return new Observable(observer => {
      this.getCorriente().subscribe(
        corriente => {
          this.getPotencia().subscribe(
            potencia => {
              observer.next({ corriente, potencia });
              observer.complete();
            },
            error => observer.error(error)
          );
        },
        error => observer.error(error)
      );
    });
  }
}
