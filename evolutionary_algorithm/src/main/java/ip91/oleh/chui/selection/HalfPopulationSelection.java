package ip91.oleh.chui.selection;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.model.Population;

import java.util.List;
import java.util.stream.Collectors;

public class HalfPopulationSelection implements Selection {

    @Override
    public List<Individual> process(Population population) {
        List<Individual> sample = population.getIndividuals().stream()
                .skip(population.getIndividuals().size() / 2)
                .collect(Collectors.toList());

        if (sample.size() % 2 == 1) {
            sample.remove(0);
        }

        return sample;
    }

}
