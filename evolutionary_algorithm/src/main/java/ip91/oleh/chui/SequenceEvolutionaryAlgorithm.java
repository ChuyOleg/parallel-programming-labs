package ip91.oleh.chui;

import ip91.oleh.chui.conditionData.BackpackConditionData;
import ip91.oleh.chui.conditionData.ConditionDataGenerator;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.model.Population;
import ip91.oleh.chui.model.Result;
import ip91.oleh.chui.populationGenerator.BackpackPopulationGenerator;
import ip91.oleh.chui.populationGenerator.SalesmanPopulationGenerator;
import ip91.oleh.chui.util.CustomPrinter;

import java.util.Random;

public class SequenceEvolutionaryAlgorithm {

    private static BackpackConditionData backpackConditionData;
    private static SalesmanConditionData salesmanConditionData;

    private static Population population;

    public static void main(String[] args) {
        initConditionData();
        initPopulation(new Random());

        EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(backpackConditionData, salesmanConditionData);

        evolutionaryAlgorithm.init();

        long startTime = System.currentTimeMillis();
        Result result = evolutionaryAlgorithm.run(population);
        long finishTime = System.currentTimeMillis();

        System.out.println("Best individual = " + result.getBestIndividual());
        System.out.println("Result fitness = " + result.getBestIndividual().getFitness());
        System.out.println("Generation count = " + result.getGeneration());
        System.out.println("That took: " + (finishTime - startTime) + " ms");
    }

    private static void initConditionData() {
        switch (Config.algorithmType) {
            case BACKPACK:
                switch (Config.conditionDataType) {
                    case RANDOM:
                        backpackConditionData = ConditionDataGenerator.backpack();
                        break;
                    case FROM_FILE:
                        backpackConditionData = CustomPrinter.readBackpackConditionDataFromFile();
                        break;
                }
                break;
            case SALESMAN:
                switch (Config.conditionDataType) {
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
        switch (Config.algorithmType) {
            case BACKPACK:
                population = new BackpackPopulationGenerator(backpackConditionData, random).generate();
                break;
            case SALESMAN:
                population = new SalesmanPopulationGenerator(salesmanConditionData, random).generate();
                break;
        }
    }

}
