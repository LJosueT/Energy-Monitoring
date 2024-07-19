package com.demoiot.demoiot.Repository;

import com.demoiot.demoiot.Entity.Datos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatosRepository extends JpaRepository<Datos, Long> {
}
