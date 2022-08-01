package ip91.oleh.chui.fitnessFunction;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SalesmanFitnessFunction implements FitnessFunction {

    private final SalesmanConditionData conditionData;

    @Override
    public int calculate(Individual individual) {
        int fitness = 0;
        int firstCity = (int) individual.getChromosome()[0];
        int lastCity = (int) individual.getChromosome()[individual.getChromosome().length - 1];
        int previousCity = firstCity;

        for (int gene = 1; gene < individual.getChromosome().length; gene++) {
            int currentCity = (int) individual.getChromosome()[gene];

            fitness += conditionData.getRoadMatrix()[previousCity][currentCity];

            previousCity = currentCity;
        }

        fitness += conditionData.getRoadMatrix()[lastCity][firstCity];

        return fitness;
    }

}
