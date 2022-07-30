package ip91.oleh.chui.selection;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;

import java.util.Arrays;
import java.util.List;

public class TwoBestSelection implements Selection {

    @Override
    public List<Individual> process(Population population) {
        Individual theBestIndividual = population.getIndividuals().last();
        Individual secondBestIndividual = population.getIndividuals().stream()
                .skip(population.getIndividuals().size() - 2).findFirst().orElseThrow(RuntimeException::new);

        return Arrays.asList(theBestIndividual, secondBestIndividual);
    }

}
