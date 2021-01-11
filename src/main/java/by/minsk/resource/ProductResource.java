package by.minsk.resource;

import by.minsk.model.Product;
import by.minsk.service.ProductService;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.NonNull;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;
import java.util.Optional;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private final ProductService productService;
    private final Integer defaultSize;

    @Inject
    public ProductResource(ProductService productService, @Named("defaultSize") Integer defaultSize) {
        this.productService = productService;
        this.defaultSize = defaultSize;
    }

    @GET
    public List<Product> getAllProducts(@QueryParam("size") Optional<Integer> size) {
        return productService.getProducts(size.orElse(defaultSize));
    }

    @GET
    @Path("/{id}")
    public Product getProductById(@PathParam("id") Long id) {
        return productService.getProductById(id);
    }

    @POST
    public Response addProduct(@NonNull @Valid Product product) {
        Product savedProduct = productService.saveProduct(product);

        return Response.created(UriBuilder.fromResource(BrandResource.class)
                .build(product))
                .entity(savedProduct)
                .build();
    }
}
