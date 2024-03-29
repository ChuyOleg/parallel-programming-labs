package ip91.oleh.chui.fitnessFunction;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.conditionData.BackpackConditionData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class BackpackFitnessFunctionTest {

    private Individual aliveIndividual;
    private Individual deadIndividual;

    private final BackpackConditionData backpackConditionData = mock(BackpackConditionData.class);

    private final FitnessFunction fitnessFunction = new BackpackFitnessFunction(backpackConditionData);

    @BeforeEach
    void init() {
        Object[] aliveChromosome = { true, false, true, true, false, false };
        Object[] deadChromosome = { true, true, true, true, false, true };

        int[] weightTable = {2, 4, 2, 3, 1, 2};
        int[] priceTable = {4, 5, 8, 2, 1, 6};
        int maxWeight = 10;

        when(backpackConditionData.getWeightTable()).thenReturn(weightTable);
        when(backpackConditionData.getPriceTable()).thenReturn(priceTable);
        when(backpackConditionData.getMaxWeight()).thenReturn(maxWeight);

        aliveIndividual = new Individual(aliveChromosome);
        deadIndividual = new Individual(deadChromosome);
    }

    @Test
    void calculateShouldReturnCorrectFitnessForAliveIndividual() {
        int expectedResult = 14;
        int actualResult = fitnessFunction.calculate(aliveIndividual);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void calculateShouldReturnCorrectFitnessForDeadIndividual() {
        int expectedResult = Integer.MIN_VALUE;
        int actualResult = fitnessFunction.calculate(deadIndividual);

        Assertions.assertEquals(expectedResult, actualResult);
    }
}