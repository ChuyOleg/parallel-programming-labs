package ip91.oleh.chui.crossover;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;
import ip91.oleh.chui.fitnessFunction.FitnessFunction;

import java.util.Random;

public class RandomPointCrossover extends AbstractPointCrossover {

    private final Random random;

    public RandomPointCrossover(FitnessFunction fitnessFunction, ChromosomeController chromosomeController, Random random) {
        super(fitnessFunction, chromosomeController);
        this.random = random;
    }

    @Override
    protected int getPoint(Individual individual) {
        return random.nextInt(individual.getChromosome().length - 1) + 1;
    }
}
