package by.minsk.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class BasicConfiguration extends Configuration {

    @Valid
    @NotNull
    private final DataSourceFactory dataSourceFactory;

    @NotNull
    private final int defaultSize;

    @JsonCreator
    public BasicConfiguration(@JsonProperty("defaultSize") int defaultSize,
                              @JsonProperty("database") DataSourceFactory dataSourceFactory) {
        this.defaultSize = defaultSize;
        this.dataSourceFactory = dataSourceFactory;
    }
}
