package com.demoiot.demoiot.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "datos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Datos {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "corriente")
    private float corriente;

    @Column(name = "potencia")
    private float potencia;

}
