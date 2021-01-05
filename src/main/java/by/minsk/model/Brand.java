package by.minsk.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Brand {
    private Long id;
    private String name;
    private Date year;

    public static BrandBuilder builder() {
        return new BrandBuilder();
    }

    public static class BrandBuilder {
        private Long id;
        private String name;
        private Date year;

        BrandBuilder() {
        }

        public BrandBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BrandBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BrandBuilder year(Date year) {
            this.year = year;
            return this;
        }

        public Brand build() {
            Brand brand = new Brand();
            brand.setId(id);
            brand.setName(name);
            brand.setYear(year);

            return brand;
        }

        public String toString() {
            return "Brand.BrandBuilder(id=" + this.id + ", name=" + this.name + ", year=" + this.year + ")";
        }
    }
}
