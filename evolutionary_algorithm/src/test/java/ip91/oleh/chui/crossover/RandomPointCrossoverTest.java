package ip91.oleh.chui.crossover;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RandomPointCrossoverTest {

    private final ChromosomeController chromosomeController = mock(ChromosomeController.class);
    private final Random random = mock(Random.class);
    private final RandomPointCrossover randomPointCrossover = new RandomPointCrossover(chromosomeController, random);

    private final Object[] chromosome = { true, true, false, true, false, false, true, false };

    @Test
    void getPointShouldReturnCorrectPoint_1() {
        Individual individual = new Individual(chromosome);

        when(random.nextInt(anyInt())).thenReturn(0);

        int expectedResult = 1;
        int actualResult = randomPointCrossover.getPoint(individual);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getPointShouldReturnCorrectPoint_2() {
        Individual individual = new Individual(chromosome);

        when(random.nextInt(anyInt())).thenReturn(3);

        int expectedResult = 4;
        int actualResult = randomPointCrossover.getPoint(individual);

        Assertions.assertEquals(expectedResult, actualResult);
    }

}