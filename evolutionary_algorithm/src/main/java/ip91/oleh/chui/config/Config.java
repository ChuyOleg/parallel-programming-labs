package ip91.oleh.chui.config;

public class Config {

    private Config() {}

    public static final AlgorithmType algorithmType = AlgorithmType.BACKPACK;
    public static final TaskType taskType = TaskType.MAXIMIZATION;
    public static final SelectionType selectionType = SelectionType.HALF_POPULATION;
    public static final CrossoverType crossoverType = CrossoverType.RANDOM_POINT;
    public static final MutationType mutationType = MutationType.OPPOSITE_VALUE;
    public static final GenerationReplacementType generationReplacementType = GenerationReplacementType.All_OFFSPRING_INTO_POPULATION;
    public static final ConditionDataType conditionDataType = ConditionDataType.FROM_FILE;

    public static final String BACKPACK_CONDITION_DATA_FILE_NAME = "src/main/resources/backpackConditionData_1.txt";
    public static final String SALESMAN_CONDITION_DATA_FILE_NAME = "src/main/resources/salesmanConditionData_1.txt";

    public static final int MAX_GENERATION_NUMBER = 5000;
    public static final int GENERATION_WITHOUT_CHANGING_LIMIT = 500;

    public static final int POPULATION_SIZE = 10;
    public static final int MUTATION_MEASURE = 100;
    public static final int MUTATION_PERCENTAGE = 1;

    /* Backpack Configuration */
    public static final int BACKPACK_THING_COUNT = 20;
    public static final int THING_MIN_WEIGHT = 1;
    public static final int THING_MAX_WEIGHT = 1000;
    public static final int BACKPACK_MAX_WEIGHT = BACKPACK_THING_COUNT * (THING_MAX_WEIGHT + THING_MIN_WEIGHT) / 3;
    public static final int THING_MIN_PRICE = 1;
    public static final int THING_MAX_PRICE = 1000;
    //*****************************

    /* Salesman Configuration */
    public static final int SALESMAN_CITY_COUNT = 500;
    public static final int SALESMAN_MIN_LENGTH = 2;
    public static final int SALESMAN_MAX_LENGTH = 10;
    //*****************************

}
