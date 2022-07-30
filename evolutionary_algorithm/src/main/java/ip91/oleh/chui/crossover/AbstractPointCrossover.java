package ip91.oleh.chui.crossover;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;
import ip91.oleh.chui.fitnessFunction.FitnessFunction;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractPointCrossover implements Crossover {

    private final FitnessFunction fitnessFunction;
    private final ChromosomeController chromosomeController;

    @Override
    public List<Individual> process(List<Individual> individuals) {
        List<Individual> offspring = new ArrayList<>();

        for (int indNum = 0; indNum < individuals.size(); indNum += 2) {
            Individual parent_1 = individuals.get(indNum);
            Individual parent_2 = individuals.get(indNum + 1);

            final int point = getPoint(parent_1);

            Object[] child_1Chromosome = chromosomeController.createUsingPointCrossover(parent_1, parent_2, point);
            Object[] child_2Chromosome = chromosomeController.createUsingPointCrossover(parent_2, parent_1, point);

            Individual individual_1 = new Individual(child_1Chromosome);
            Individual individual_2 = new Individual(child_2Chromosome);

            individual_1.setFitness(fitnessFunction.calculate(individual_1));
            individual_2.setFitness(fitnessFunction.calculate(individual_2));

            addToOffspringIfIsAlive(individual_1, offspring);
            addToOffspringIfIsAlive(individual_2, offspring);
        }

        return offspring;
    }

    private void addToOffspringIfIsAlive(Individual individual, List<Individual> offspring) {
        if (individual.getFitness() > Integer.MIN_VALUE) {
            offspring.add(individual);
        }
    }

    protected abstract int getPoint(Individual individual);

}
