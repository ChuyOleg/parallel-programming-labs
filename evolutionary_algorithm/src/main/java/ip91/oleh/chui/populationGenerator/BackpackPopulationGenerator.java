package ip91.oleh.chui.populationGenerator;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.model.Population;
import ip91.oleh.chui.conditionData.BackpackConditionData;
import ip91.oleh.chui.config.Config;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
@Getter
public class BackpackPopulationGenerator implements PopulationGenerator {

    private final BackpackConditionData conditionData;
    private final Random random;

    @Override
    public Population generate() {
        List<Individual> individuals = new ArrayList<>(Config.POPULATION_SIZE);

        for (int individualNum = 0; individualNum < Config.POPULATION_SIZE; individualNum++) {
            Object[] chromosome = new Object[conditionData.getWeightTable().length];
            int backpackCurrentWeight = 0;
            int price = 0;

            for (int gene = 0; gene < conditionData.getWeightTable().length; gene++) {
                boolean chance = random.nextInt(2) == 0;
                int backpackNewWeight = backpackCurrentWeight + conditionData.getWeightTable()[gene];

                if (chance && backpackNewWeight <= conditionData.getMaxWeight()) {
                    chromosome[gene] = true;
                    backpackCurrentWeight = backpackNewWeight;
                    price += conditionData.getPriceTable()[gene];
                } else {
                    chromosome[gene] = false;
                }
            }
            Individual individual = new Individual(chromosome);
            individual.setFitness(price);
            individuals.add(individual);
        }


        return new Population(individuals);
    }

}
