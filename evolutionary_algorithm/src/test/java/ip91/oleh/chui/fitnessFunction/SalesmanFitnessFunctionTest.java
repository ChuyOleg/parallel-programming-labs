package ip91.oleh.chui.fitnessFunction;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SalesmanFitnessFunctionTest {

    private Individual individual;

    private final SalesmanConditionData salesmanConditionData = mock(SalesmanConditionData.class);

    private final FitnessFunction fitnessFunction = new SalesmanFitnessFunction(salesmanConditionData);

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
        Object[] chromosome = { 2, 3, 1 };
        individual = new Individual(chromosome);

        int expectedResult = 17;
        int actualResult = fitnessFunction.calculate(individual);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateOneShouldReturnCorrectFitness_2() {
        Object[] chromosome = { 1, 2, 3 };
        individual = new Individual(chromosome);

        int expectedResult = 23;
        int actualResult = fitnessFunction.calculate(individual);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateOneShouldReturnCorrectFitness_3() {
        Object[] chromosome = { 3, 2, 1 };
        individual = new Individual(chromosome);

        int expectedResult = 23;
        int actualResult = fitnessFunction.calculate(individual);

        Assertions.assertEquals(expectedResult, actualResult);
    }


}