package enums;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Rating fromString(String value) {
        for (Rating rating : Rating.values()) {
            if (rating.value.equals(value)) {
                return rating;
            }
        }
        return null;
    }
}
