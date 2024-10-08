package com.example.pruebanequi.controller;

import com.example.pruebanequi.model.Franquicia;
import com.example.pruebanequi.model.Producto;
import com.example.pruebanequi.model.Sucursal;
import com.example.pruebanequi.service.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    // 1. Agregar una nueva franquicia
    @PostMapping
    public ResponseEntity<Franquicia> agregarFranquicia(@RequestBody Franquicia franquicia) {
        return ResponseEntity.ok(franquiciaService.save(franquicia));
    }

    // 2. Agregar una nueva sucursal a una franquicia
    @PostMapping("/{franquiciaId}/sucursales")
    public ResponseEntity<Sucursal> agregarSucursal(@PathVariable Long franquiciaId, @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(franquiciaService.addSucursal(franquiciaId, sucursal));
    }

    // 3. Agregar un nuevo producto a una sucursal
    @PostMapping("/{franquiciaId}/sucursales/{sucursalId}/productos")
    public ResponseEntity<Producto> agregarProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @RequestBody Producto producto) {
        return ResponseEntity.ok(franquiciaService.addProducto(sucursalId, producto));
    }

    // 4. Eliminar un producto de una sucursal
    @DeleteMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long productoId) {
        franquiciaService.deleteProducto(productoId);
        return ResponseEntity.noContent().build();
    }

    // 5. Modificar el stock de un producto
    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/stock")
    public ResponseEntity<Producto> modificarStock(@PathVariable Long productoId, @RequestBody int nuevoStock) {
        return ResponseEntity.ok(franquiciaService.updateStock(productoId, nuevoStock));
    }

    // 6. Obtener el producto con mayor stock en cada sucursal de una franquicia
    @GetMapping("/{franquiciaId}/productos-mayor-stock")
    public ResponseEntity<List<Producto>> productoConMayorStock(@PathVariable Long franquiciaId) {
        return ResponseEntity.ok(franquiciaService.findProductoConMayorStock(franquiciaId));
    }

    // 7. Actualizar el nombre de una franquicia
    @PutMapping("/{franquiciaId}")
    public ResponseEntity<Franquicia> actualizarFranquicia(@PathVariable Long franquiciaId, @RequestBody String nuevoNombre) {
        return ResponseEntity.ok(franquiciaService.updateFranquiciaNombre(franquiciaId, nuevoNombre));
    }

    // 8. Actualizar el nombre de una sucursal
    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}")
    public ResponseEntity<Sucursal> actualizarSucursal(@PathVariable Long sucursalId, @RequestBody String nuevoNombre) {
        return ResponseEntity.ok(franquiciaService.updateSucursalNombre(sucursalId, nuevoNombre));
    }

    // 9. Actualizar el nombre de un producto
    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long productoId, @RequestBody String nuevoNombre) {
        return ResponseEntity.ok(franquiciaService.updateProductoNombre(productoId, nuevoNombre));
    }
}
