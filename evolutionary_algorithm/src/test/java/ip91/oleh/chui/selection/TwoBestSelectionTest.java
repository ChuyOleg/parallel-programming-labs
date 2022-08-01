package ip91.oleh.chui.selection;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

class TwoBestSelectionTest {

    private final Selection selection = new TwoBestSelection();
    private Population population;
    private Individual individualFitness_1;
    private Individual individualFitness_2;
    private Individual individualFitness_3;
    private Individual individualFitness_4;
    private Individual individualFitness_5;

    @BeforeEach
    void init() {
        individualFitness_1 = new Individual(null, 1);
        individualFitness_2 = new Individual(null, 2);
        individualFitness_3 = new Individual(null, 3);
        individualFitness_4 = new Individual(null, 4);
        individualFitness_5 = new Individual(null, 5);
    }

    @Test
    void processBackpackPopulationShouldReturnCorrectSelection() {
        TreeSet<Individual> individualTreeSet = new TreeSet<>(getMaximizationComparator());
        individualTreeSet.add(individualFitness_1);
        individualTreeSet.add(individualFitness_3);
        individualTreeSet.add(individualFitness_2);
        individualTreeSet.add(individualFitness_4);
        individualTreeSet.add(individualFitness_5);
        population = new Population(individualTreeSet);

        List<Individual> expectedResult = Arrays.asList(individualFitness_4, individualFitness_5);
        List<Individual> actualResult = selection.process(population);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void processSalesmanPopulationShouldReturnCorrectSelection() {
        TreeSet<Individual> individualTreeSet = new TreeSet<>(getMinimizationComparator());
        individualTreeSet.add(individualFitness_1);
        individualTreeSet.add(individualFitness_3);
        individualTreeSet.add(individualFitness_2);
        individualTreeSet.add(individualFitness_4);
        individualTreeSet.add(individualFitness_5);
        population = new Population(individualTreeSet);

        List<Individual> expectedResult = Arrays.asList(individualFitness_2, individualFitness_1);
        List<Individual> actualResult = selection.process(population);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    private Comparator<Individual> getMaximizationComparator() {
        return Comparator.comparingInt(Individual::getFitness);
    }

    private Comparator<Individual> getMinimizationComparator() {
        return (ch1, ch2) -> ch2.getFitness() - ch1.getFitness();
    }
}