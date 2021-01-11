package by.minsk;

import by.minsk.config.BasicConfiguration;
import by.minsk.config.factory.BrandDaoFactory;
import by.minsk.config.factory.MysqlJdbiFactory;
import by.minsk.config.factory.ProductDaoFactory;
import by.minsk.dao.BrandDao;
import by.minsk.dao.ProductDao;
import by.minsk.exception.mapper.NotFoundExceptionMapper;
import by.minsk.health.ApplicationHealthCheck;
import by.minsk.resource.BrandResource;
import by.minsk.resource.ProductResource;
import by.minsk.service.BrandService;
import by.minsk.service.BrandServiceImpl;
import by.minsk.service.ProductService;
import by.minsk.service.ProductServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;

public class IntroductionApplication extends Application<BasicConfiguration> {
    @Override
    public void run(BasicConfiguration configuration, Environment environment) {
        environment.jersey().register(BrandResource.class);
        environment.jersey().register(ProductResource.class);
        environment.jersey().register(new NotFoundExceptionMapper());

        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                registerServicesImpls();
                registerFactories();
                putNamedValueIntoDIContext();
                putObjectsIntoDIContext();
            }

            private void registerServicesImpls() {
                bind(BrandServiceImpl.class).to(BrandService.class).in(Singleton.class);
                bind(ProductServiceImpl.class).to(ProductService.class).in(Singleton.class);
            }

            private void registerFactories() {
                bindFactory(MysqlJdbiFactory.class, Singleton.class)
                        .to(Jdbi.class)
                        .in(Singleton.class);
                bindFactory(BrandDaoFactory.class, Singleton.class)
                        .to(BrandDao.class)
                        .in(Singleton.class);
                bindFactory(ProductDaoFactory.class, Singleton.class)
                        .to(ProductDao.class)
                        .in(Singleton.class);
            }

            private void putNamedValueIntoDIContext() {
                bind(configuration.getDefaultSize()).named("defaultSize");
            }

            private void putObjectsIntoDIContext() {
                bind(configuration.getDataSourceFactory());
                bind(environment);
            }
        });

        environment.healthChecks().register("application", new ApplicationHealthCheck());
    }

    public static void main(String[] args) throws Exception {
        new IntroductionApplication().run("server", "config.yml");
    }

    @Override
    public void initialize(Bootstrap<BasicConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }
}
