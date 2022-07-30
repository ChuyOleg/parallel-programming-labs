package ip91.oleh.chui.populationGenerator;

import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;
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

    @Override
    public Population generate(int size) {
        Individual[] individuals = new Individual[size];
        Random random = new Random();

        for (int individualNum = 0; individualNum < size; individualNum++) {
            Object[] chromosome = new Object[Config.SALESMAN_CITY_COUNT - 1];
            List<Integer> freeCity = IntStream.range(1, Config.SALESMAN_CITY_COUNT).boxed().collect(Collectors.toList());
            int fitness = 0;
            int previousCity = 0;

            for (int gene = 0; gene < Config.SALESMAN_CITY_COUNT - 1; gene++) {
                int randomCityIndex = random.nextInt(freeCity.size());
                int randomCity = freeCity.get(randomCityIndex);
                chromosome[gene] = randomCity;
                fitness -= conditionData.getRoadMatrix()[previousCity][randomCity];
                previousCity = randomCity;
                freeCity.remove(randomCityIndex);
            }
            fitness -= conditionData.getRoadMatrix()[previousCity][0];

            Individual individual = new Individual(chromosome);
            individual.setFitness(fitness);
            individuals[individualNum] = individual;
        }

        return new Population(individuals);
    }

}
