package ip91.oleh.chui.fitnessFunction;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.conditionData.BackpackConditionData;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BackpackFitnessFunction implements FitnessFunction {

    private final BackpackConditionData conditionData;

    @Override
    public int calculate(Individual individual) {
        int fitness = 0;
        int weight = 0;

        for (int gene = 0; gene < individual.getChromosome().length; gene++) {
            boolean isInBackpack = (boolean) individual.getChromosome()[gene];

            if (isInBackpack) {
                fitness += conditionData.getPriceTable()[gene];
                weight += conditionData.getWeightTable()[gene];
            }
        }

        if (isDead(weight)) {
            return Integer.MIN_VALUE;
        }

        return fitness;
    }

    private boolean isDead(int weight) {
        return weight > conditionData.getMaxWeight();
    }
}
