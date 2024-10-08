package com.example.pruebanequi.service;

import com.example.pruebanequi.model.Franquicia;
import com.example.pruebanequi.model.Producto;
import com.example.pruebanequi.model.Sucursal;
import com.example.pruebanequi.repository.FranquiciaRepository;
import com.example.pruebanequi.repository.ProductoRepository;
import com.example.pruebanequi.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // 1. Guardar una nueva franquicia
    public Franquicia save(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    // 2. Agregar una sucursal a una franquicia
    public Sucursal addSucursal(Long franquiciaId, Sucursal sucursal) {
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        sucursal.setFranquicia(franquicia);
        return sucursalRepository.save(sucursal);
    }

    // 3. Agregar un producto a una sucursal
    public Producto addProducto(Long sucursalId, Producto producto) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        producto.setSucursal(sucursal);
        return productoRepository.save(producto);
    }

    // 4. Eliminar un producto
    public void deleteProducto(Long productoId) {
        productoRepository.deleteById(productoId);
    }

    // 5. Modificar el stock de un producto
    public Producto updateStock(Long productoId, int nuevoStock) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }

    // 6. Encontrar el producto con mayor stock en cada sucursal de una franquicia
    public List<Producto> findProductoConMayorStock(Long franquiciaId) {
        List<Sucursal> sucursales = sucursalRepository.findByFranquiciaId(franquiciaId);
        return sucursales.stream()
                .map(sucursal -> {
                    List<Producto> productos = productoRepository.findBySucursalId(sucursal.getId());
                    return productos.stream()
                            .max(Comparator.comparingInt(Producto::getStock))
                            .orElse(null); // Retorna null si no hay productos en la sucursal
                })
                .collect(Collectors.toList());
    }

    // 7. Actualizar el nombre de una franquicia
    public Franquicia updateFranquiciaNombre(Long franquiciaId, String nuevoNombre) {
        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada"));
        franquicia.setNombre(nuevoNombre);
        return franquiciaRepository.save(franquicia);
    }

    // 8. Actualizar el nombre de una sucursal
    public Sucursal updateSucursalNombre(Long sucursalId, String nuevoNombre) {
        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
        sucursal.setNombre(nuevoNombre);
        return sucursalRepository.save(sucursal);
    }

    // 9. Actualizar el nombre de un producto
    public Producto updateProductoNombre(Long productoId, String nuevoNombre) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.setNombre(nuevoNombre);
        return productoRepository.save(producto);
    }
}
