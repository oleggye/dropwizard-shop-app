package by.minsk.dao.mapper;

import by.minsk.model.Brand;
import by.minsk.model.Product;
import by.minsk.model.ProductType;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    //todo: use DI container for BrandMapper

    @Override
    public Product map(ResultSet rs, StatementContext ctx) throws SQLException {
        long id = rs.getLong("pr.id");
        String title = rs.getString("pr.title");

        String productTypeValue = rs.getString("pr.productType");
        ProductType productType = ProductType.fromString(productTypeValue);
        BigDecimal price = rs.getBigDecimal("pr.price");

        long brandId = rs.getLong("pr.brand_id");
        Brand brand = Brand.builder()
                .id(brandId)
                .build();

        return Product.builder()
                .id(id)
                .title(title)
                .productType(productType)
                .brand(brand)
                .price(price)
                .build();
    }
}
