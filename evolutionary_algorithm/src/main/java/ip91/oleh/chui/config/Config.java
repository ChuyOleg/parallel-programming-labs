package ip91.oleh.chui.config;

public class Config {

    private Config() {}

    public static final int POPULATION_SIZE = 20;
    public static final int MUTATION_PERCENTAGE = 5;

    public static final int BACKPACK_THING_COUNT = 100;

    public static final int THING_MIN_WEIGHT = 2;
    public static final int THING_MAX_WEIGHT = 10;

    public static final int BACKPACK_MAX_WEIGHT = BACKPACK_THING_COUNT * (THING_MAX_WEIGHT + THING_MIN_WEIGHT) / 3;

    public static final int THING_MIN_PRICE = 3;
    public static final int THING_MAX_PRICE = 15;

    public static final int SALESMAN_CITY_COUNT = 8;

    public static final int SALESMAN_MIN_LENGTH = 2;
    public static final int SALESMAN_MAX_LENGTH = 10;

    public static final int GENERATION_WITHOUT_CHANGING_LIMIT = 100;

}
