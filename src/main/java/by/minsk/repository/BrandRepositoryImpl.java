package by.minsk.repository;

import by.minsk.model.Brand;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BrandRepositoryImpl implements BrandRepository {
    private final List<Brand> brands;

    public BrandRepositoryImpl(List<Brand> brands) {
        this.brands = ImmutableList.copyOf(brands);
    }

    @Override
    public List<Brand> findAll(int size) {
        return brands.stream()
                .limit(size)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brands.stream()
                .filter(brand -> brand.getId().equals(id))
                .findFirst();
    }

    @Override
    public long save(Brand brand) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
