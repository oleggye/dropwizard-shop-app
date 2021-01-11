package by.minsk.dao.mapper;

import by.minsk.model.Brand;
import by.minsk.model.Product;
import org.jdbi.v3.core.result.LinkedHashMapRowReducer;
import org.jdbi.v3.core.result.RowView;

import java.util.Map;

public class ProductBrandRowReducer implements LinkedHashMapRowReducer<Long, Product> {
    @Override
    public void accumulate(Map<Long, Product> container, RowView rowView) {
        Product product = container.computeIfAbsent(rowView.getColumn("pr.id", Long.class),
                id -> rowView.getRow(Product.class));

        if (rowView.getColumn("br.id", Long.class) != null) {
            product.setBrand(rowView.getRow(Brand.class));
        }
    }
}
