package by.minsk.dao;

import by.minsk.dao.mapper.BrandMapper;
import by.minsk.dao.mapper.ProductBrandRowReducer;
import by.minsk.dao.mapper.ProductMapper;
import by.minsk.model.Product;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(ProductMapper.class)
@RegisterRowMapper(BrandMapper.class)
public interface JdbiProductDao extends ProductDao {

    String SELECT_ALL_PRODUCTS = "select pr.id, pr.title, pr.productType, pr.brand_id, pr.price, br.id, br.name," +
            " br.year from product as pr join brand as br on pr.brand_id = br.id";
    String SELECT_PRODUCT_BY_ID = SELECT_ALL_PRODUCTS + " where pr.id = :productId";

    @Override
    @SqlQuery(SELECT_ALL_PRODUCTS + " ORDER BY pr.id LIMIT :size")
    @UseRowReducer(ProductBrandRowReducer.class)
    List<Product> findAll(@Bind("size") int size);

    @Override
    @SqlQuery(SELECT_PRODUCT_BY_ID)
    @UseRowReducer(ProductBrandRowReducer.class)
    Optional<Product> findById(@Bind("productId") Long productId);

    @Override
    @SqlUpdate("insert into product (title, productType, brand_id, price) " +
            "values (:product.title, :product.productType, :product.brand_id, :product.price)")
    @GetGeneratedKeys
    long save(@BindBean("product") Product product);
}
