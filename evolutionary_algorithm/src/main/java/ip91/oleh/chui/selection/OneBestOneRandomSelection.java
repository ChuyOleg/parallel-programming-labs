package ip91.oleh.chui.selection;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OneBestOneRandomSelection implements Selection {

    @Override
    public List<Individual> process(Population population) {
        Individual theBestIndividual = population.getIndividuals()[0];
        Individual randomIndividual = null;
        Random random = new Random();

        for (Individual individual : population.getIndividuals()) {
            if (individual.getFitness() > theBestIndividual.getFitness()) {
                theBestIndividual = individual;
            }
        }

        while (randomIndividual == null) {
            int randomIndex = random.nextInt(population.getIndividuals().length);
            if (population.getIndividuals()[randomIndex] != theBestIndividual) {
                randomIndividual = population.getIndividuals()[randomIndex];
            }
        }

        return Arrays.asList(theBestIndividual, randomIndividual);
    }

}
