package ip91.oleh.chui.crossover;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;


public class FairPointCrossover extends AbstractPointCrossover {

    public FairPointCrossover(ChromosomeController chromosomeController) {
        super(chromosomeController);
    }

    @Override
    protected int getPoint(Individual individual) {
        return individual.getChromosome().length / 2;
    }

}
