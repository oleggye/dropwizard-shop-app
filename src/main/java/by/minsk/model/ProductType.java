package by.minsk.model;

import java.util.Arrays;

public enum ProductType {
    T_SHIRT, PANTS, HAT;

    public static ProductType fromString(String value) {
        String upperCaseValue = toUpperCase(value);

        return Arrays.stream(ProductType.values())
                .filter(val -> val.name().equals(upperCaseValue))
                .findFirst()
                .orElse(null);
    }

    private static String toUpperCase(String value) {
        return value != null ? value.toUpperCase() : null;
    }
}
