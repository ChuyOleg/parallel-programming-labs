package ip91.oleh.chui.crossover.chromosomeController;

import ip91.oleh.chui.model.Individual;

public interface ChromosomeController {

    Object[] createUsingPointCrossover(Individual parent1, Individual parent2, int point);

}
