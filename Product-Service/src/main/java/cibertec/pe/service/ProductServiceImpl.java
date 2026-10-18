package cibertec.pe.service;

import cibertec.pe.entity.Product;
import cibertec.pe.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public List<Product> listarTodos() {
        return productRepository.findByActiveTrue();
    }

    @Override
    public List<Product> listarTodosConInactivos() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> listarPorCategoria(String categoria) {
        if (categoria == null || categoria.equalsIgnoreCase("all") || categoria.trim().isEmpty()) {
            return productRepository.findByActiveTrue();
        }
        return productRepository.findByCategoryIgnoreCaseAndActiveTrue(categoria.trim());
    }

    @Override
    public Optional<Product> buscarPorId(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product guardar(Product producto) {
        if (producto.getActive() == null) {
            producto.setActive(true);
        }
        if (producto.getRating() == null) {
            producto.setRating(5.0);
        }
        if (producto.getReviewsCount() == null) {
            producto.setReviewsCount(0);
        }
        return productRepository.save(producto);
    }

    @Override
    public Product actualizar(Long id, Product producto) {
        return productRepository.findById(id).map(existente -> {
            if (producto.getName() != null) existente.setName(producto.getName());
            if (producto.getShortName() != null) existente.setShortName(producto.getShortName());
            if (producto.getCategory() != null) existente.setCategory(producto.getCategory());
            if (producto.getCategoryLabel() != null) existente.setCategoryLabel(producto.getCategoryLabel());
            if (producto.getBrand() != null) existente.setBrand(producto.getBrand()); // <-- AGREGAR ESTA LÍNEA
            if (producto.getPrice() != null) existente.setPrice(producto.getPrice());
            if (producto.getOriginalPrice() != null) existente.setOriginalPrice(producto.getOriginalPrice());
            if (producto.getImage() != null) existente.setImage(producto.getImage());
            if (producto.getTag() != null) existente.setTag(producto.getTag());
            if (producto.getTagColor() != null) existente.setTagColor(producto.getTagColor());
            if (producto.getStock() != null) existente.setStock(producto.getStock());
            if (producto.getSku() != null) existente.setSku(producto.getSku());
            if (producto.getDescription() != null) existente.setDescription(producto.getDescription());
            if (producto.getRating() != null) existente.setRating(producto.getRating());
            if (producto.getReviewsCount() != null) existente.setReviewsCount(producto.getReviewsCount());
            if (producto.getActive() != null) existente.setActive(producto.getActive());
            return productRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }


    @Override
    public void eliminar(Long id) {
        productRepository.findById(id).ifPresent(p -> {
            p.setActive(false);
            productRepository.save(p);
        });
    }
}
