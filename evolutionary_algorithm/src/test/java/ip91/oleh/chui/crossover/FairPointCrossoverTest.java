package ip91.oleh.chui.crossover;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;
import ip91.oleh.chui.fitnessFunction.FitnessFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FairPointCrossoverTest {

    private final FitnessFunction fitnessFunction = mock(FitnessFunction.class);
    private final ChromosomeController chromosomeController = mock(ChromosomeController.class);
    private final FairPointCrossover fairPointCrossover = new FairPointCrossover(fitnessFunction, chromosomeController);

    private List<Individual> parents_1;
    private List<Individual> parents_2;

    @BeforeEach
    void init() {
        Individual parent1 = new Individual(new Object[]{ true, true, false, false, false, true });
        Individual parent2 = new Individual(new Object[]{ false, true, true, false, true, false });
        Individual parent3 = new Individual(new Object[]{ false, false, true, true, true, false });
        Individual parent4 = new Individual(new Object[]{ true, false, true, false, true, false });

        parents_1 = Arrays.asList(parent1, parent2);
        parents_2 = Arrays.asList(parent1, parent2, parent3, parent4);
    }

    @Test
    void getPointForEvenChromosomeShouldReturnCorrectPoint() {
        Object[] evenChromosome = { true, true, false, true, false, false, true, false };
        Individual individualWithEvenChromosome = new Individual(evenChromosome);

        int expectedResult = 4;
        int actualResult = fairPointCrossover.getPoint(individualWithEvenChromosome);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getPointForOddChromosomeShouldReturnCorrectPoint() {
        Object[] oddChromosome = { true, true, false, true, false, false, true };
        Individual individualWithOddChromosome = new Individual(oddChromosome);

        int expectedResult = 3;
        int actualResult = fairPointCrossover.getPoint(individualWithOddChromosome);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    // TODO: uncomment based on code <AbstractPointCrossover: 34 line>
//    @Test
//    void processShouldReturnEmptyList() {
//        when(fitnessFunction.calculate(any())).thenReturn(Integer.MIN_VALUE);
//
//        List<Individual> actualOffspring = fairPointCrossover.process(parents_1);
//
//        Assertions.assertEquals(0, actualOffspring.size());
//    }

    @Test
    void processShouldReturnCorrectOffspring_1() {
        Object[] chromosome_1 = { true, true, false, false, true, false };
        Object[] chromosome_2 = { false, true, true, false, false, true };

        when(chromosomeController.createUsingPointCrossover(eq(parents_1.get(0)), eq(parents_1.get(1)), anyInt()))
                .thenReturn(chromosome_1);
        when(chromosomeController.createUsingPointCrossover(eq(parents_1.get(1)), eq(parents_1.get(0)), anyInt()))
                .thenReturn(chromosome_2);

        List<Individual> expectedOffspring = Arrays.asList(
                new Individual(chromosome_1),
                new Individual(chromosome_2)
        );

        List<Individual> actualOffspring = fairPointCrossover.process(parents_1);

        for (int index = 0; index < actualOffspring.size(); index++) {
               Assertions.assertArrayEquals(
                       expectedOffspring.get(index).getChromosome(), actualOffspring.get(index).getChromosome()
               );
        }
    }

    @Test
    void processShouldReturnCorrectOffspring_2() {
        Object[] chromosome_1 = { true, true, false, false, true, false };
        Object[] chromosome_2 = { false, true, true, false, false, true };
        Object[] chromosome_3 = { false, false, true, false, true, false };
        Object[] chromosome_4 = { true, false, true, true, true, false };

        when(chromosomeController.createUsingPointCrossover(eq(parents_2.get(0)), eq(parents_2.get(1)), anyInt()))
                .thenReturn(chromosome_1);
        when(chromosomeController.createUsingPointCrossover(eq(parents_2.get(1)), eq(parents_2.get(0)), anyInt()))
                .thenReturn(chromosome_2);
        when(chromosomeController.createUsingPointCrossover(eq(parents_2.get(2)), eq(parents_2.get(3)), anyInt()))
                .thenReturn(chromosome_3);
        when(chromosomeController.createUsingPointCrossover(eq(parents_2.get(3)), eq(parents_2.get(2)), anyInt()))
                .thenReturn(chromosome_4);

        List<Individual> expectedOffspring = Arrays.asList(
                new Individual(chromosome_1),
                new Individual(chromosome_2),
                new Individual(chromosome_3),
                new Individual(chromosome_4)
        );

        List<Individual> actualOffspring = fairPointCrossover.process(parents_2);

        for (int index = 0; index < actualOffspring.size(); index++) {
            Assertions.assertArrayEquals(
                    expectedOffspring.get(index).getChromosome(), actualOffspring.get(index).getChromosome()
            );
        }
    }
}
