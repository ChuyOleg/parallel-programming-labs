package ip91.oleh.chui;

import ip91.oleh.chui.conditionData.BackpackConditionData;
import ip91.oleh.chui.crossover.Crossover;
import ip91.oleh.chui.crossover.FairPointCrossover;
import ip91.oleh.chui.crossover.chromosomeController.BackpackChromosomeController;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;
import ip91.oleh.chui.fitnessFunction.BackpackFitnessFunction;
import ip91.oleh.chui.fitnessFunction.FitnessFunction;
import ip91.oleh.chui.generationReplacement.AllOffspringIntoPopulationGenerationReplacement;
import ip91.oleh.chui.generationReplacement.GenerationReplacement;
import ip91.oleh.chui.mutation.Mutation;
import ip91.oleh.chui.mutation.OppositeBooleanValueMutation;
import ip91.oleh.chui.populationGenerator.BackpackPopulationGenerator;
import ip91.oleh.chui.populationGenerator.PopulationGenerator;
import ip91.oleh.chui.selection.HalfPopulationSelection;
import ip91.oleh.chui.selection.Selection;
import ip91.oleh.chui.util.CustomPrinter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Builder
public class MainBackpack {

//    private Population population;
//    private Individual fittest;
//    private int generationCount = 0;

    private final BackpackConditionData backpackData;
    private final FitnessFunction fitnessFunction;
    private final Population population;
    private final PopulationGenerator populationGenerator;
    private final Selection selection;
    private final ChromosomeController chromosomeController;
    private final Crossover crossover;
    private final Mutation mutation;
    private final GenerationReplacement generationReplacement;

    public static void main(String[] args) throws IOException {
        BackpackConditionData backpackData = CustomPrinter.readBackpackConditionDataFromFile();
        BackpackFitnessFunction backpackFitnessFunction = new BackpackFitnessFunction(backpackData);
        PopulationGenerator populationGenerator = new BackpackPopulationGenerator(backpackData);
        Population population = populationGenerator.generate(backpackData.getWeightTable().length);
        Selection halfPopulationSelection = new HalfPopulationSelection();
        ChromosomeController chromosomeController = new BackpackChromosomeController();
        Crossover fairCrossover = new FairPointCrossover(backpackFitnessFunction, chromosomeController);
        Mutation oppositeBooleanValueMutation = new OppositeBooleanValueMutation(backpackFitnessFunction);
        GenerationReplacement generationReplacement = new AllOffspringIntoPopulationGenerationReplacement();

        MainBackpack mainBackpack = MainBackpack.builder()
                .backpackData(backpackData)
                .fitnessFunction(backpackFitnessFunction)
                .populationGenerator(populationGenerator)
                .population(population)
                .selection(halfPopulationSelection)
                .chromosomeController(chromosomeController)
                .crossover(fairCrossover)
                .mutation(oppositeBooleanValueMutation)
                .generationReplacement(generationReplacement)
                .build();


        mainBackpack.run();
    }

    public void run() {
        System.out.println("Population Before = " + population);

        for (int i = 0; i < 200; i++) {
            List<Individual> bestParents = selection.process(population);

            List<Individual> offspring = crossover.process(bestParents);

            mutation.process(offspring);

            generationReplacement.process(population, offspring);

        }

        System.out.println("Population After = " + population);

    }

}
