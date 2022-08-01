package ip91.oleh.chui.fitnessFunction;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SalesmanFitnessFunctionTest {

    private Individual individual;

    private final SalesmanConditionData salesmanConditionData = mock(SalesmanConditionData.class);

    private final SalesmanFitnessFunction salesmanFitnessFunction = new SalesmanFitnessFunction(salesmanConditionData);

    @BeforeEach
    void init() {
        int[][] roadMatrix = {
                {Integer.MAX_VALUE, 3, 5, 9},
                {3, Integer.MAX_VALUE, 4, 2},
                {5, 4, Integer.MAX_VALUE, 7},
                {9, 2, 7, Integer.MAX_VALUE}
        };

        when(salesmanConditionData.getRoadMatrix()).thenReturn(roadMatrix);
    }

    @Test
    void calculateOneShouldReturnCorrectFitness() {
        Object[] chromosome = { 2, 0, 3, 1 };
        individual = new Individual(chromosome);

        int expectedResult = 20;
        int actualResult = salesmanFitnessFunction.calculate(individual);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateOneShouldReturnCorrectFitness_2() {
        Object[] chromosome = { 1, 0, 2, 3 };
        individual = new Individual(chromosome);

        int expectedResult = 17;
        int actualResult = salesmanFitnessFunction.calculate(individual);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateOneShouldReturnCorrectFitness_3() {
        Object[] chromosome = { 3, 2, 0, 1 };
        individual = new Individual(chromosome);

        int expectedResult = 17;
        int actualResult = salesmanFitnessFunction.calculate(individual);

        Assertions.assertEquals(expectedResult, actualResult);
    }


}