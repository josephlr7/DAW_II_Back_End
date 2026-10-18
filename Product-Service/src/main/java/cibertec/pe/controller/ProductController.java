package cibertec.pe.controller;

import cibertec.pe.entity.Product;
import cibertec.pe.service.CloudinaryService;
import cibertec.pe.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private CloudinaryService  cloudinaryService;

    // 1. Listar productos (con filtro opcional por categoría o inclusión de inactivos para el Admin)
    @GetMapping
    public ResponseEntity<List<Product>> listarProductos(
            @RequestParam(required = false) String category,
            @RequestParam(required = false, defaultValue = "false") boolean includeInactive) {
        if (includeInactive) {
            return ResponseEntity.ok(productService.listarTodosConInactivos());
        }
        if (category != null && !category.trim().isEmpty()) {
            return ResponseEntity.ok(productService.listarPorCategoria(category));
        }
        return ResponseEntity.ok(productService.listarTodos());
    }

    // 2. Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerProducto(@PathVariable Long id) {
        return productService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear nuevo producto (CRUD Admin)
    @PostMapping
    public ResponseEntity<Product> crearProducto(@RequestBody Product producto) {
        Product nuevo = productService.guardar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // 4. Actualizar producto existente (CRUD Admin)
    @PutMapping("/{id}")
    public ResponseEntity<Product> actualizarProducto(@PathVariable Long id, @RequestBody Product producto) {
        try {
            return ResponseEntity.ok(productService.actualizar(id, producto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Eliminar producto (Borrado lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> subirImagenProducto(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío.");
            }
            String urlImagen = cloudinaryService.subirImagen(file);
            return ResponseEntity.ok(java.util.Map.of("url", urlImagen));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir a Cloudinary: " + e.getMessage());
        }
    }
}
