package by.minsk;

import by.minsk.model.Brand;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("Not working due to dropwizard start-up")
@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {

    private static final String CONFIG_PATH = ResourceHelpers.resourceFilePath("test-example.yml");
    private static DropwizardAppExtension<TestConfiguration> RULE = new DropwizardAppExtension<>(
            TestApplication.class, CONFIG_PATH);

    @BeforeAll
    public static void migrateDb() throws Exception {
        RULE.getApplication().run(CONFIG_PATH);
    }

    @Test
    public void test() {
        //Client client = RULE.client();
        String uri = String.format("http://localhost:%s/brands", RULE.getLocalPort());

        Brand brand = ClientBuilder.newClient()
                .target(UriBuilder.fromUri("http://localhost")
                        .port(RULE.getLocalPort())
                        .path("brands")
                        .build())
                .request()
                .get(Brand.class);

        assertThat(brand).isNull();
    }
}
