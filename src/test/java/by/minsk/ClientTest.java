package by.minsk;

import by.minsk.service.BrandService;
import io.dropwizard.testing.junit5.DropwizardClientExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ClientTest {

    private DropwizardClientExtension extension;

    @BeforeEach
    public void setUp() {
        BrandService mock = Mockito.mock(BrandService.class);
        extension = new DropwizardClientExtension(new PingResource());
        return;
    }

    @Test
    public void shouldPing() throws IOException {
        final URL url = new URL(extension.baseUri() + "/ping");
        final String response = new BufferedReader(new InputStreamReader(url.openStream())).readLine();
        assertEquals("pong", response);
    }

    @Path("/ping")
    public static class PingResource {
        @GET
        public String ping() {
            return "pong";
        }
    }
}
