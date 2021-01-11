package by.minsk.service;

import by.minsk.dao.BrandDao;
import by.minsk.exception.NotFoundException;
import by.minsk.model.Brand;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BrandServiceImpl implements BrandService {
    private final BrandDao brandDao;

    @Override
    public List<Brand> getBrands(int size) {
        return brandDao.findAll(size);
    }

    @Override
    public Brand getBrandById(long id) {
        return brandDao.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Can't find brand with id=%s", id)));
    }

    @Override
    public Brand saveBrand(Brand brand) {
        long brandId = brandDao.save(brand);
        return getBrandById(brandId);
    }
}
