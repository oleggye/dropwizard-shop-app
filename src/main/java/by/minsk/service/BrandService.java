package by.minsk.service;

import by.minsk.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrands(int size);

    Brand getBrandById(long id);

    Brand saveBrand(Brand brand);
}
