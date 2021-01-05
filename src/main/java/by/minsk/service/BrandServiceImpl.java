package by.minsk.service;

import by.minsk.exception.NotFoundException;
import by.minsk.model.Brand;
import by.minsk.repository.BrandRepository;

import javax.inject.Inject;
import java.util.List;

public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Inject
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getBrands(int size) {
        return brandRepository.findAll(size);
    }

    @Override
    public Brand getBrandById(long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Can't find brand with id=%s", id)));
    }

    @Override
    public Brand saveBrand(Brand brand) {
        long brandId = brandRepository.save(brand);
        return getBrandById(brandId);
    }
}
