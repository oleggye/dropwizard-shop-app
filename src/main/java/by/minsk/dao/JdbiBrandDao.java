package by.minsk.dao;

import by.minsk.dao.mapper.BrandMapper;
import by.minsk.model.Brand;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(BrandMapper.class)
public interface JdbiBrandDao extends BrandDao {

    String SELECT_ALL_BRANDS = "select br.id, br.name, br.year from brand as br";

    @Override
    @SqlQuery(SELECT_ALL_BRANDS + " ORDER BY br.id LIMIT :size")
    List<Brand> findAll(@Bind("size") int size);

    @Override
    @SqlQuery(SELECT_ALL_BRANDS + " where br.id = :id")
    Optional<Brand> findById(@Bind("id") Long id);

    @Override
    @SqlUpdate("insert into brand (brand.name, brand.year) values (:name, :year)")
    @GetGeneratedKeys
    long save(@BindBean Brand brand);
}
