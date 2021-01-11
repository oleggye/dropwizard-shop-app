package by.minsk.dao;

import by.minsk.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> findAll(int size);

    Optional<Product> findById(Long productId);

    long save(Product product);
}
