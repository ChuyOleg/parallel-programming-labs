package ip91.oleh.chui;

import ip91.oleh.chui.conditionData.BackpackConditionData;
import ip91.oleh.chui.conditionData.ConditionDataGenerator;
import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.crossover.Crossover;
import ip91.oleh.chui.crossover.FairPointCrossover;
import ip91.oleh.chui.crossover.chromosomeController.BackpackChromosomeController;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;
import ip91.oleh.chui.fitnessFunction.BackpackFitnessFunction;
import ip91.oleh.chui.generationReplacement.AllOffspringIntoPopulationGenerationReplacement;
import ip91.oleh.chui.generationReplacement.GenerationReplacement;
import ip91.oleh.chui.mutation.Mutation;
import ip91.oleh.chui.mutation.OppositeBooleanValueMutation;
import ip91.oleh.chui.populationGenerator.BackpackPopulationGenerator;
import ip91.oleh.chui.populationGenerator.PopulationGenerator;
import ip91.oleh.chui.selection.HalfPopulationSelection;
import ip91.oleh.chui.selection.Selection;
import ip91.oleh.chui.util.CustomPrinter;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Builder
public class MainBackpack {

    private final Population population;
    private final Selection selection;
    private final Crossover crossover;
    private final Mutation mutation;
    private final GenerationReplacement generationReplacement;

    private Individual bestIndividual;
    private int bestIndividualNotChangeCounter;

    public static void main(String[] args) throws IOException {
        BackpackConditionData backpackData = ConditionDataGenerator.backpack();
//        BackpackConditionData backpackData = CustomPrinter.readBackpackConditionDataFromFile();
        BackpackFitnessFunction backpackFitnessFunction = new BackpackFitnessFunction(backpackData);
        PopulationGenerator populationGenerator = new BackpackPopulationGenerator(backpackData);
        Population population = populationGenerator.generate(Config.POPULATION_SIZE);
        Selection halfPopulationSelection = new HalfPopulationSelection();
        ChromosomeController chromosomeController = new BackpackChromosomeController();
        Crossover fairCrossover = new FairPointCrossover(backpackFitnessFunction, chromosomeController);
        Mutation oppositeBooleanValueMutation = new OppositeBooleanValueMutation(backpackFitnessFunction);
        GenerationReplacement generationReplacement = new AllOffspringIntoPopulationGenerationReplacement();

        MainBackpack mainBackpack = MainBackpack.builder()
                .population(population)
                .selection(halfPopulationSelection)
                .crossover(fairCrossover)
                .mutation(oppositeBooleanValueMutation)
                .generationReplacement(generationReplacement)
                .build();


        mainBackpack.run();
    }

    public void run() {
        System.out.println(population.getIndividuals().last().getFitness());

        for (int i = 0; i < 10000; i++) {
            List<Individual> bestParents = selection.process(population);

            List<Individual> offspring = crossover.process(bestParents);

            mutation.process(offspring);

            generationReplacement.process(population, offspring);

            changeBestIndividualIfPossibleMaximization(population);
        }

        System.out.println("Best = " + bestIndividual);
    }

    private void changeBestIndividualIfPossibleMaximization(Population population) {
        Individual bestInPopulation = population.getIndividuals().last();

        if (bestIndividual == null || bestInPopulation.getFitness() > bestIndividual.getFitness()) {
            bestIndividual = bestInPopulation;
            System.out.println("Changed");
            bestIndividualNotChangeCounter = 0;
        } else {
            bestIndividualNotChangeCounter++;
        }
    }

    private void changeBestIndividualIfPossibleMinimization(Population population) {
        Individual bestInPopulation = population.getIndividuals().last();

        if (bestIndividual == null || bestInPopulation.getFitness() < bestIndividual.getFitness()) {
            bestIndividual = bestInPopulation;
            bestIndividualNotChangeCounter = 0;
        } else {
            bestIndividualNotChangeCounter++;
        }
    }

}
