package by.minsk.config.factory;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import lombok.extern.java.Log;
import org.glassfish.hk2.api.Factory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.SqlStatements;

import javax.inject.Inject;

@Log
public class MysqlJdbiFactory implements Factory<Jdbi> {
    private final Jdbi jdbi;

    @Inject
    public MysqlJdbiFactory(DataSourceFactory dataSourceFactory,
                            Environment environment) {
        JdbiFactory jdbiFactory = new JdbiFactory();
        Jdbi jdbi = jdbiFactory.build(environment, dataSourceFactory, "mysql");

        jdbi.getConfig(SqlStatements.class)
                .setUnusedBindingAllowed(true);
        this.jdbi = jdbi;
    }

    @Override
    public Jdbi provide() {
        return jdbi;
    }

    @Override
    public void dispose(Jdbi instance) {
        //do nothing
        log.info("dispose was invoked");
    }
}
