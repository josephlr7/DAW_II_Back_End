package cibertec.pe.service;

import cibertec.pe.entity.Product;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> listarTodos();
    List<Product> listarTodosConInactivos();
    List<Product> listarPorCategoria(String categoria);
    Optional<Product> buscarPorId(Long id);
    Product guardar(Product producto);
    Product actualizar(Long id, Product producto);
    void eliminar(Long id);
}
