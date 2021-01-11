package by.minsk.config.factory;

import by.minsk.dao.JdbiProductDao;
import by.minsk.dao.ProductDao;
import lombok.extern.java.Log;
import org.glassfish.hk2.api.Factory;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Inject;

@Log
public class ProductDaoFactory implements Factory<ProductDao> {
    private final ProductDao productDao;

    @Inject
    public ProductDaoFactory(Jdbi jdbi) {
        this.productDao = jdbi.onDemand(JdbiProductDao.class);
    }

    @Override
    public ProductDao provide() {
        return productDao;
    }

    @Override
    public void dispose(ProductDao instance) {
        //do nothing
        log.info("dispose was invoked");
    }
}
