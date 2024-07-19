import { Component, OnInit } from '@angular/core';
import { DatosService } from "../../service/datos.service";
import { Datos } from "../../models/datos.model";
import { interval, Subscription } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  datos: Datos[] = [];
  private actualizacionSubscription: Subscription | null = null;

  constructor(private datosService: DatosService) {}

  ngOnInit(): void {
    this.iniciarActualizacionContinua();
  }

  ngOnDestroy(): void {
    this.detenerActualizacion();
  }

  iniciarActualizacionContinua(): void {
    // Actualiza los datos cada 5 segundos
    this.actualizacionSubscription = interval(5000)
      .pipe(
        switchMap(() => this.datosService.getDatos())
      )
      .subscribe(
        (datosDto) => {
          const nuevosDatos = new Datos(datosDto.corriente, datosDto.potencia);
          this.datos.push(nuevosDatos);
        },
        (error) => {
          console.error('Error al obtener los datos:', error);
        }
      );
  }

  detenerActualizacion(): void {
    if (this.actualizacionSubscription) {
      this.actualizacionSubscription.unsubscribe();
      this.actualizacionSubscription = null;
    }
  }

  reiniciarDatos(): void {
    this.datos = [];
  }

}
