package com.example.pruebanequi.repository;

import com.example.pruebanequi.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findBySucursalId(Long sucursalId);
}
