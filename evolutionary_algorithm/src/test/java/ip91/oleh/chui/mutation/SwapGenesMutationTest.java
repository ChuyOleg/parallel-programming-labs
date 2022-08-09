package ip91.oleh.chui.mutation;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.fitnessFunction.FitnessFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SwapGenesMutationTest {

    private final FitnessFunction fitnessFunction = mock(FitnessFunction.class);
    private final Random random = mock(Random.class);

    private final Mutation mutation = new SwapGenesMutation(fitnessFunction, random);

    private List<Individual> offspring;

    @BeforeEach
    void init() {
        Individual child1 = new Individual(new Object[]{ true, false, false, true });
        Individual child2 = new Individual(new Object[]{ false, false, true, false });

        offspring = Arrays.asList(child1, child2);
    }

    @Test
    void processShouldChangeIndividuals() {
        when(random.nextInt(Config.MUTATION_MEASURE)).thenReturn(Config.MUTATION_PERCENTAGE - 1);
        when(random.nextInt(offspring.get(0).getChromosome().length)).thenReturn(0);

        mutation.process(offspring);

        Object[] expectedChromosome1 = { true, true, false, false };

        assertArrayEquals(expectedChromosome1, offspring.get(0).getChromosome());

        Object[] expectedChromosome2 = { false, false, false, true };

        assertArrayEquals(expectedChromosome2, offspring.get(1).getChromosome());
    }

    @Test
    void processShouldNotChangeIndividuals() {
        when(random.nextInt(anyInt())).thenReturn(Config.MUTATION_PERCENTAGE + 1);

        mutation.process(offspring);

        Object[] expectedChromosome1 = { true, false, false, true };

        assertArrayEquals(expectedChromosome1, offspring.get(0).getChromosome());

        Object[] expectedChromosome2 = { false, false, true, false };

        assertArrayEquals(expectedChromosome2, offspring.get(1).getChromosome());

    }

}