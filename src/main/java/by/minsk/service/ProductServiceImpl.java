package by.minsk.service;

import by.minsk.dao.ProductDao;
import by.minsk.exception.NotFoundException;
import by.minsk.model.Product;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Override
    @Transaction
    public List<Product> getProducts(int size) {
        List<Product> all = productDao.findAll(size);

//        List<Long> brandIds = all.stream()
//                .map(Product::getBrand)
//                .map(Brand::getId)
//                .distinct()
//                .collect(Collectors.toList());

        return all;
    }

    @Override
    @Transaction
    public Product getProductById(long id) {
        Product product = productDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Can't find product with id=%s", id)));

//        Brand brand = brandDao.findById(product.getId()).get();
//
//        product.setBrand(brand);
        return product;
    }

    //todo: think about transactions
    @Override
    public Product saveProduct(Product product) {
        long id = productDao.save(product);
        return getProductById(id);
    }
}
