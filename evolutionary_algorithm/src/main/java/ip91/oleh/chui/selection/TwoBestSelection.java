package ip91.oleh.chui.selection;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;

import java.util.Arrays;
import java.util.List;

public class TwoBestSelection implements Selection {

    @Override
    public List<Individual> process(Population population) {
        Individual theBestIndividual = null;
        Individual secondBestIndividual = null;

        for (Individual individual : population.getIndividuals()) {
            if (secondBestIndividual == null || individual.getFitness() > secondBestIndividual.getFitness()) {
                if (theBestIndividual == null || individual.getFitness() > theBestIndividual.getFitness()) {
                    secondBestIndividual = theBestIndividual;
                    theBestIndividual = individual;
                } else {
                    secondBestIndividual = individual;
                }
            }
        }

        return Arrays.asList(theBestIndividual, secondBestIndividual);
    }

}
