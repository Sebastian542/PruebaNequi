package com.example.pruebanequi.repository;

import com.example.pruebanequi.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    @Query("SELECT s FROM Sucursal s WHERE s.franquicia.id = :franquiciaId")
    List<Sucursal> findByFranquiciaId(Long franquiciaId);
}
