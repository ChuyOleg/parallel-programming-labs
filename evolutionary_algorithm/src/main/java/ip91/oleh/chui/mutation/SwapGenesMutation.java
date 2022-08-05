package ip91.oleh.chui.mutation;

import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.Individual;
import ip91.oleh.chui.fitnessFunction.FitnessFunction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class SwapGenesMutation implements Mutation {

    private final FitnessFunction fitnessFunction;
    private final Random random;

    @Override
    public void process(List<Individual> individuals) {
        for (Individual individual : individuals) {
            for (int geneNum = 0; geneNum < individual.getChromosome().length; geneNum++) {
                boolean chance = random.nextInt(Config.MUTATION_MEASURE) < Config.MUTATION_PERCENTAGE;
                if (chance) {
                    int randomGeneNum = random.nextInt(individual.getChromosome().length);
                    Object tempGene = individual.getChromosome()[geneNum];
                    individual.getChromosome()[geneNum] = individual.getChromosome()[randomGeneNum];
                    individual.getChromosome()[randomGeneNum] = tempGene;
                }
            }

            individual.setFitness(fitnessFunction.calculate(individual));
        }
    }

}
