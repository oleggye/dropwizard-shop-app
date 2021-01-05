package by.minsk.repository;

import by.minsk.model.Brand;
import by.minsk.repository.mapper.BrandMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowMapper;

import java.util.List;
import java.util.Optional;

public interface JdbiBrandRepository extends BrandRepository {

    @Override
    @SqlQuery("select br.id, br.name, br.year from brand as br")
    @UseRowMapper(BrandMapper.class)
    List<Brand> findAll(int size);

    @Override
    @SqlQuery("select  br.id, br.name, br.year from brand as br where br.id = :id")
    @UseRowMapper(BrandMapper.class)
    Optional<Brand> findById(@Bind("id") Long id);

    @Override
    @SqlUpdate("insert into brand (name, year) values (:name, :year)")
    @GetGeneratedKeys
    long save(@BindBean Brand brand);
}
