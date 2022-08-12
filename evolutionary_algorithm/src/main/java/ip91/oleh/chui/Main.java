package ip91.oleh.chui;

import ip91.oleh.chui.conditionData.BackpackConditionData;
import ip91.oleh.chui.conditionData.ConditionDataGenerator;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import ip91.oleh.chui.config.AlgorithmType;
import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.config.TaskName;
import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.model.Population;
import ip91.oleh.chui.model.Result;
import ip91.oleh.chui.populationGenerator.BackpackPopulationGenerator;
import ip91.oleh.chui.populationGenerator.SalesmanPopulationGenerator;
import ip91.oleh.chui.util.CustomPrinter;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        printAlgorithmInfo();

        Result result = warmUpMachine();

        if (Config.TEST_PERFORMANCE_ITERATION_NUM > 0) {
            BigDecimal averageTime = testPerformance();
            System.out.println("Average execution time for " + Config.TEST_PERFORMANCE_ITERATION_NUM + " iteration: " + (averageTime) +  " ms" );
        }

        System.out.println("Best fitness: " + result.getBestIndividual().getFitness());
        System.out.println("Generation: " + result.getGeneration());
    }

    private static Result warmUpMachine() throws ExecutionException, InterruptedException {
        switch (Config.ALGORITHM_TYPE) {
            case SEQUENCE:
                return runSequenceAlgorithm();
            case PARALLEL:
                return runParallelAlgorithm();
            default:
                throw new RuntimeException();
        }
    }

    private static BigDecimal testPerformance() throws ExecutionException, InterruptedException {
        BigDecimal fullTime = BigDecimal.valueOf(0);

        long start;
        long finish;
        for (int iteration = 0; iteration < Config.TEST_PERFORMANCE_ITERATION_NUM; iteration++) {
            switch (Config.ALGORITHM_TYPE) {
                case SEQUENCE:
                    start = System.currentTimeMillis();
                    runSequenceAlgorithm();
                    finish = System.currentTimeMillis();
                    fullTime = fullTime.add(BigDecimal.valueOf(finish - start));
                    break;
                case PARALLEL:
                    start = System.currentTimeMillis();
                    runParallelAlgorithm();
                    finish = System.currentTimeMillis();
                    fullTime = fullTime.add(BigDecimal.valueOf(finish - start));
                    break;
            }
        }

        return fullTime.divide(BigDecimal.valueOf(Config.TEST_PERFORMANCE_ITERATION_NUM), RoundingMode.CEILING);
    }

    private static Result runSequenceAlgorithm() {
        SequenceEvolutionaryAlgorithm sequenceEvolutionaryAlgorithm = new SequenceEvolutionaryAlgorithm(
                backpackConditionData, salesmanConditionData, createPopulationCopy(population)
        );
        return sequenceEvolutionaryAlgorithm.process();
    }

    private static Result runParallelAlgorithm() throws ExecutionException, InterruptedException {
        ParallelEvolutionaryAlgorithm parallelEvolutionaryAlgorithm = new ParallelEvolutionaryAlgorithm(
                backpackConditionData, salesmanConditionData, createPopulationCopy(population)
        );
        return parallelEvolutionaryAlgorithm.process();
    }

    private static void initConditionData() {
        switch (Config.TASK_NAME) {
            case BACKPACK:
                switch (Config.CONDITION_DATA_TYPE) {
                    case RANDOM:
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

    private static void printAlgorithmInfo() {
        final String algorithmType = (Config.ALGORITHM_TYPE.equals(AlgorithmType.SEQUENCE)) ? "Sequence" : "Parallel CPU_CORE = " + Config.CORE_CPU_NUMBER;
        final String thingName = (Config.TASK_NAME.equals(TaskName.SALESMAN)) ? "cities" : "things";
        final int thingCount = (Config.TASK_NAME.equals(TaskName.SALESMAN)) ? salesmanConditionData.getRoadMatrix().length : backpackConditionData.getWeightTable().length;

        System.out.printf(
                "%s: [data = %s | Max Generation = %d | Generation without changes limit = %d | Population size = %d | %s = %d]\n" +
                        "Algorithm type = %s \n" +
                        "selection = %s \n" +
                        "crossover = %s \n" +
                        "mutation = %s  \n\n",
                Config.TASK_NAME,
                Config.CONDITION_DATA_TYPE,
                Config.MAX_GENERATION_NUMBER,
                Config.GENERATION_WITHOUT_CHANGING_LIMIT,
                Config.POPULATION_SIZE,
                thingName,
                thingCount,
                algorithmType,
                Config.SELECTION_TYPE,
                Config.CROSSOVER_TYPE,
                Config.MUTATION_TYPE
        );
    }

}
