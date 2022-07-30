package ip91.oleh.chui.populationGenerator;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;
import ip91.oleh.chui.conditionData.BackpackConditionData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

@RequiredArgsConstructor
@Getter
public class BackpackPopulationGenerator implements PopulationGenerator {

    private final BackpackConditionData conditionData;

    @Override
    public Population generate(int size) {
        TreeSet<Individual> individuals = new TreeSet<>(Comparator.comparingInt(Individual::getFitness));
        Random random = new Random();

        for (int individualNum = 0; individualNum < size; individualNum++) {
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
