package by.minsk.dao;

import by.minsk.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandDao {
    List<Brand> findAll(int size);

    Optional<Brand> findById(Long id);

    long save(Brand brand);
}
