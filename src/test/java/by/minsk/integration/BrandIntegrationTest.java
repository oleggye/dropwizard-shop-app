package by.minsk;

import by.minsk.config.BasicConfiguration;
import by.minsk.model.Brand;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
public class BrandIntegrationTest {

    private static DropwizardAppExtension<BasicConfiguration> RULE = new DropwizardAppExtension<>(
            IntroductionApplication.class, "config.yml");

    @Test
    public void shouldGetAllBrands() {
        URI brands = UriBuilder.fromUri("http://localhost")
                .port(RULE.getLocalPort())
                .path("brands").build();

        List<Brand> result = RULE.client()
                .target(brands)
                .request()
                .get(new GenericType<List<Brand>>() {
                });
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(3);
    }

    @Test
    public void shouldGetBrandById() {
        URI brands = UriBuilder.fromUri("http://localhost")
                .port(RULE.getLocalPort())
                .path("brands/1").build();

        Brand result = RULE.client()
                .target(brands)
                .request()
                .get(Brand.class);
        assertThat(result).isNotNull();
    }
}
