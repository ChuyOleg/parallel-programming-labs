package ip91.oleh.chui.selection;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.model.Population;

import java.util.Arrays;
import java.util.List;

public class TwoBestSelection implements Selection {

    @Override
    public List<Individual> process(Population population) {
        Individual theBestIndividual = population.getIndividuals().get(population.getIndividuals().size() - 1);
        Individual secondBestIndividual = population.getIndividuals().get(population.getIndividuals().size() - 2);

        return Arrays.asList(secondBestIndividual, theBestIndividual);
    }

}
