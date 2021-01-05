package by.minsk;

import by.minsk.config.BasicConfiguration;
import by.minsk.config.BrandRepositoryFactory;
import by.minsk.exception.mapper.NotFoundExceptionMapper;
import by.minsk.health.ApplicationHealthCheck;
import by.minsk.repository.BrandRepository;
import by.minsk.resource.BrandResource;
import by.minsk.service.BrandService;
import by.minsk.service.BrandServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

public class IntroductionApplication extends Application<BasicConfiguration> {
    @Override
    public void run(BasicConfiguration configuration, Environment environment) {
        environment.jersey().register(BrandResource.class);
        environment.jersey().register(new NotFoundExceptionMapper());

//        BrandRepository brandRepository = new BrandRepositoryFactory()
//                .build(configuration.getDataSourceFactory(), environment);

        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                // register impl for interface
                bind(BrandServiceImpl.class).to(BrandService.class).in(Singleton.class);
                //register manually created bean
//                bind(brandRepository).to(BrandRepository.class);
                //register value as named bean
                bind(configuration.getDefaultSize()).named("defaultSize");

                //register factory for BrandRepository and dependencies for it
                bindFactory(BrandRepositoryFactory.class, Singleton.class)
                        .to(BrandRepository.class)
                        .in(Singleton.class);
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
