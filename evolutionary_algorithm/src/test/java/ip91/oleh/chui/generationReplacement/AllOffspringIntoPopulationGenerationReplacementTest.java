package ip91.oleh.chui.generationReplacement;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class AllOffspringIntoPopulationGenerationReplacementTest {

    private final GenerationReplacement generationReplacement = new AllOffspringIntoPopulationGenerationReplacement();

    private Individual parent_1;
    private Individual parent_2;
    private Individual parent_3;
    private Individual parent_4;
    private Individual aliveChild_1;
    private Individual aliveChild_2;
    private Individual deadChild;

    @BeforeEach
    void init() {
        parent_1 = new Individual(null, 10);
        parent_2 = new Individual(null, 20);
        parent_3 = new Individual(null, 30);
        parent_4 = new Individual(null, 40);

        aliveChild_1 = new Individual(null, 15);
        aliveChild_2 = new Individual(null, 25);
        deadChild = new Individual(null, Integer.MIN_VALUE);
    }

    @Test
    void processShouldWorkCorrectlyForBackpack() {
        TreeSet<Individual> individuals = new TreeSet<>(getMaximizationComparator());
        individuals.addAll(Arrays.asList(parent_1, parent_2, parent_3, parent_4));
        Population population = new Population(individuals);

        List<Individual> offspring = Arrays.asList(aliveChild_1, aliveChild_2, deadChild);

        generationReplacement.process(population, offspring);

        Assertions.assertEquals(4, population.getIndividuals().size());
        Assertions.assertTrue(
                population.getIndividuals().containsAll(
                        Arrays.asList(aliveChild_1, aliveChild_2, parent_3, parent_4)
                )
        );
    }

    @Test
    void processShouldWorkCorrectlyForSalesman() {
        TreeSet<Individual> individuals = new TreeSet<>(getMinimizationComparator());
        individuals.addAll(Arrays.asList(parent_1, parent_2, parent_3, parent_4));
        Population population = new Population(individuals);

        List<Individual> offspring = Arrays.asList(aliveChild_1, aliveChild_2, deadChild);

        generationReplacement.process(population, offspring);

        Assertions.assertEquals(4, population.getIndividuals().size());
        Assertions.assertTrue(
                population.getIndividuals().containsAll(
                        Arrays.asList(aliveChild_1, aliveChild_2, parent_1, parent_2)
                )
        );
    }

    private Comparator<Individual> getMaximizationComparator() {
        return Comparator.comparingInt(Individual::getFitness);
    }

    private Comparator<Individual> getMinimizationComparator() {
        return (ch1, ch2) -> ch2.getFitness() - ch1.getFitness();
    }

}
