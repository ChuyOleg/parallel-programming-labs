package ip91.oleh.chui.crossover;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;

import java.util.Random;

public class RandomPointCrossover extends AbstractPointCrossover {

    private final Random random;

    public RandomPointCrossover(ChromosomeController chromosomeController, Random random) {
        super(chromosomeController);
        this.random = random;
    }

    @Override
    protected int getPoint(Individual individual) {
        return random.nextInt(individual.getChromosome().length - 1) + 1;
    }
}
