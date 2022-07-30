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
        int previousCity = 0;
        int lastCity = (int) individual.getChromosome()[individual.getChromosome().length - 1];

        for (int gene = 0; gene < individual.getChromosome().length; gene++) {
            int currentCity = (int) individual.getChromosome()[gene];

            fitness -= conditionData.getRoadMatrix()[previousCity][currentCity];

            previousCity = currentCity;
        }

        fitness -= conditionData.getRoadMatrix()[lastCity][0];

        return fitness;
    }

}
