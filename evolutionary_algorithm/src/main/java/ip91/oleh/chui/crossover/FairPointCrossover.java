package ip91.oleh.chui.crossover;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;
import ip91.oleh.chui.fitnessFunction.FitnessFunction;


public class FairPointCrossover extends AbstractPointCrossover {

    public FairPointCrossover(FitnessFunction fitnessFunction, ChromosomeController chromosomeController) {
        super(fitnessFunction, chromosomeController);
    }

    @Override
    protected int getPoint(Individual individual) {
        return individual.getChromosome().length / 2;
    }

}
