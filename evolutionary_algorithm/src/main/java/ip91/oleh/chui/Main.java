package ip91.oleh.chui;

import ip91.oleh.chui.conditionData.BackpackConditionData;
import ip91.oleh.chui.conditionData.ConditionDataGenerator;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.model.Population;
import ip91.oleh.chui.populationGenerator.BackpackPopulationGenerator;
import ip91.oleh.chui.populationGenerator.SalesmanPopulationGenerator;
import ip91.oleh.chui.util.CustomPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Main {

    private static BackpackConditionData backpackConditionData;
    private static SalesmanConditionData salesmanConditionData;

    private static Population population;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        initConditionData();
        initPopulation(new Random());

        switch (Config.ALGORITHM_TYPE) {
            case SEQUENCE:
                SequenceEvolutionaryAlgorithm sequenceEvolutionaryAlgorithm = new SequenceEvolutionaryAlgorithm(
                        backpackConditionData, salesmanConditionData, createPopulationCopy(population)
                );
                sequenceEvolutionaryAlgorithm.process();
                break;
            case PARALLEL:
                ParallelEvolutionaryAlgorithm parallelEvolutionaryAlgorithm = new ParallelEvolutionaryAlgorithm(
                        backpackConditionData, salesmanConditionData, createPopulationCopy(population)
                );
                parallelEvolutionaryAlgorithm.process();
                break;
        }
    }

    private static void initConditionData() {
        switch (Config.TASK_NAME) {
            case BACKPACK:
                switch (Config.CONDITION_DATA_TYPE) {
                    case RANDOM:
                        System.out.printf(
                                "Backpack: [Random data | Max Generation = %d | Generation without changes limit = %d | Population size = %d | things = %d]\n" +
                                        "Algorithm type = %s \n" +
                                        "selection = %s \n" +
                                        "crossover = %s \n" +
                                        "mutation = %s  \n\n",
                                Config.MAX_GENERATION_NUMBER,
                                Config.GENERATION_WITHOUT_CHANGING_LIMIT,
                                Config.POPULATION_SIZE,
                                Config.BACKPACK_THING_COUNT,
                                Config.ALGORITHM_TYPE,
                                Config.SELECTION_TYPE,
                                Config.CROSSOVER_TYPE,
                                Config.MUTATION_TYPE
                        );
                        backpackConditionData = ConditionDataGenerator.backpack();
                        break;
                    case FROM_FILE:
                        backpackConditionData = CustomPrinter.readBackpackConditionDataFromFile();
                        break;
                }
                break;
            case SALESMAN:
                switch (Config.CONDITION_DATA_TYPE) {
                    case RANDOM:
                        salesmanConditionData = ConditionDataGenerator.salesman();
                        break;
                    case FROM_FILE:
                        salesmanConditionData = CustomPrinter.readSalesmanConditionDataFromFile();
                        break;
                }
                break;
        }
    }

    private static void initPopulation(Random random) {
        switch (Config.TASK_NAME) {
            case BACKPACK:
                population = new BackpackPopulationGenerator(backpackConditionData, random).generate();
                break;
            case SALESMAN:
                population = new SalesmanPopulationGenerator(salesmanConditionData, random).generate();
                break;
        }
    }

    private static Population createPopulationCopy(Population population) {
        List<Individual> individualList = new ArrayList<>(population.getIndividuals());

        return new Population(individualList);
    }

}
