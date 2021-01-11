package by.minsk.service;

import by.minsk.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(int size);

    Product getProductById(long id);

    Product saveProduct(Product product);
}
