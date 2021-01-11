package by.minsk.config.factory;

import by.minsk.dao.BrandDao;
import by.minsk.dao.JdbiBrandDao;
import lombok.extern.java.Log;
import org.glassfish.hk2.api.Factory;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Inject;

@Log
public class BrandDaoFactory implements Factory<BrandDao> {
    private final BrandDao brandDao;

    @Inject
    public BrandDaoFactory(Jdbi jdbi) {
        this.brandDao = jdbi.onDemand(JdbiBrandDao.class);
    }

    @Override
    public BrandDao provide() {
        return brandDao;
    }

    @Override
    public void dispose(BrandDao instance) {
        //do nothing
        log.info("dispose was invoked");
    }
}
