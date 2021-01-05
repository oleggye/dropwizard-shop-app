package by.minsk.config;

import by.minsk.repository.BrandRepository;
import by.minsk.repository.JdbiBrandRepository;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import lombok.extern.java.Log;
import org.glassfish.hk2.api.Factory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.SqlStatements;

import javax.inject.Inject;

@Log
public class BrandRepositoryFactory implements Factory<BrandRepository> {
    private final BrandRepository brandRepository;

    @Inject
    public BrandRepositoryFactory(DataSourceFactory dataSourceFactory, Environment environment) {
        JdbiFactory jdbiFactory = new JdbiFactory();
        Jdbi jdbi = jdbiFactory.build(environment, dataSourceFactory, "mysql");

        jdbi.getConfig(SqlStatements.class)
                .setUnusedBindingAllowed(true);

        this.brandRepository = jdbi.onDemand(JdbiBrandRepository.class);
    }

    @Override
    public BrandRepository provide() {
        log.info("Provide brandRepository");
        return brandRepository;
    }

    @Override
    public void dispose(BrandRepository instance) {
        //do nothing
        log.info("dispose was invoked");
    }
}
