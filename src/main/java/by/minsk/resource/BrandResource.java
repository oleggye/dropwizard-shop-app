package by.minsk.resource;

import by.minsk.model.Brand;
import by.minsk.service.BrandService;
import lombok.NonNull;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;
import java.util.Optional;

@Path("brands")
@Produces(MediaType.APPLICATION_JSON)
public class BrandResource {
    private final BrandService brandService;
    private final Integer defaultSize;

    @Inject
    public BrandResource(BrandService brandService, @Named("defaultSize") Integer defaultSize) {
        this.brandService = brandService;
        this.defaultSize = defaultSize;
    }

    @GET
    public List<Brand> getBrands(@QueryParam("size") Optional<Integer> size) {
        return brandService.getBrands(size.orElse(defaultSize));
    }

    @GET
    @Path("/{id}")
    public Brand getBrandById(@PathParam("id") Long id) {
        return brandService.getBrandById(id);
    }

    @POST
    public Response addBrand(@NonNull @Valid Brand brand) {
        Brand savedBrand = brandService.saveBrand(brand);

        return Response.created(UriBuilder.fromResource(BrandResource.class)
                .build(brand))
                .entity(savedBrand)
                .build();
    }
}
