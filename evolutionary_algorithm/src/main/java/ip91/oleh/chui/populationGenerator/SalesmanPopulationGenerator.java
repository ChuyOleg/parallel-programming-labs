package ip91.oleh.chui.populationGenerator;

import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.model.Population;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
public class SalesmanPopulationGenerator implements PopulationGenerator{

    private final SalesmanConditionData conditionData;
    private final Random random;

    @Override
    public Population generate() {
        List<Individual> individuals = new ArrayList<>(Config.POPULATION_SIZE);

        for (int individualNum = 0; individualNum < Config.POPULATION_SIZE; individualNum++) {
            Object[] chromosome = new Object[conditionData.getRoadMatrix().length - 1];
            List<Integer> freeCity = IntStream.range(1, conditionData.getRoadMatrix().length).boxed().collect(Collectors.toList());
            int fitness = 0;
            int previousCity = 0;

            for (int gene = 0; gene < conditionData.getRoadMatrix().length - 1; gene++) {
                int randomCityIndex = random.nextInt(freeCity.size());
                int randomCity = freeCity.get(randomCityIndex);
                chromosome[gene] = randomCity;
                fitness += conditionData.getRoadMatrix()[previousCity][randomCity];
                previousCity = randomCity;
                freeCity.remove(randomCityIndex);
            }
            fitness += conditionData.getRoadMatrix()[previousCity][0];

            Individual individual = new Individual(chromosome);
            individual.setFitness(fitness);
            individuals.add(individual);
        }

        return new Population(individuals);
    }

}
