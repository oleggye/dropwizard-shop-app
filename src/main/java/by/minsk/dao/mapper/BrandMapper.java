package by.minsk.dao.mapper;

import by.minsk.model.Brand;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandMapper implements RowMapper<Brand>, ColumnMapper<Brand> {

    @Override
    public Brand map(ResultSet rs, StatementContext ctx) throws SQLException {

        long id = rs.getLong("br.id");
        String name = rs.getString("br.name");
        Date year = rs.getDate("br.year");

        return Brand.builder()
                .id(id)
                .name(name)
                .year(year)
                .build();
    }

    @Override
    public Brand map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        return this.map(rs, ctx);
    }
}
