package ip91.oleh.chui;

import ip91.oleh.chui.conditionData.BackpackConditionData;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.config.GenerationReplacementType;
import ip91.oleh.chui.crossover.Crossover;
import ip91.oleh.chui.crossover.FairPointCrossover;
import ip91.oleh.chui.crossover.RandomPointCrossover;
import ip91.oleh.chui.crossover.chromosomeController.BackpackChromosomeController;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;
import ip91.oleh.chui.crossover.chromosomeController.SalesmanChromosomeController;
import ip91.oleh.chui.fitnessFunction.BackpackFitnessFunction;
import ip91.oleh.chui.fitnessFunction.FitnessFunction;
import ip91.oleh.chui.fitnessFunction.SalesmanFitnessFunction;
import ip91.oleh.chui.generationReplacement.AllOffspringIntoPopulationGenerationReplacement;
import ip91.oleh.chui.generationReplacement.GenerationReplacement;
import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.model.Population;
import ip91.oleh.chui.model.Result;
import ip91.oleh.chui.mutation.Mutation;
import ip91.oleh.chui.mutation.OppositeBooleanValueMutation;
import ip91.oleh.chui.mutation.SwapGenesMutation;
import ip91.oleh.chui.selection.HalfPopulationSelection;
import ip91.oleh.chui.selection.OneBestOneRandomSelection;
import ip91.oleh.chui.selection.Selection;
import ip91.oleh.chui.selection.TwoBestSelection;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class EvolutionaryAlgorithm {

    private final BackpackConditionData backpackConditionData;
    private final SalesmanConditionData salesmanConditionData;

    private Individual bestIndividual;
    private int bestIndividualNotChangeCounter;

    private Selection selection;
    private Crossover crossover;
    private Mutation mutation;
    private GenerationReplacement generationReplacement;

    public void init() {
        FitnessFunction fitnessFunction = getFitnessFunction();
        ChromosomeController chromosomeController = getChromosomeController();
        Random random = new Random();

        initSelection(random);
        initCrossover(chromosomeController, random);
        initMutation(fitnessFunction, random);
        initGenerationReplacement();
    }

    public Result run(Population population) {
        sortPopulationBasedOnTaskType(population);
        int generationCounter;

        for (generationCounter = 0; generationCounter < Config.MAX_GENERATION_NUMBER; generationCounter++) {
            List<Individual> bestParents = selection.process(population);

            List<Individual> offspring = crossover.process(bestParents);

            mutation.process(offspring);

            generationReplacement.process(population, offspring);

            sortPopulationBasedOnTaskType(population);

            changeBestIndividualBasedOnTaskTypeIfPossible(population);

            if (bestIndividualNotChangeCounter == Config.GENERATION_WITHOUT_CHANGING_LIMIT) break;
        }

        return new Result(bestIndividual, generationCounter);
    }

    private FitnessFunction getFitnessFunction() {
        switch (Config.algorithmType) {
            case BACKPACK:
                return new BackpackFitnessFunction(backpackConditionData);
            case SALESMAN:
                return new SalesmanFitnessFunction(salesmanConditionData);
            default:
                throw new RuntimeException();
        }
    }

    private ChromosomeController getChromosomeController() {
        switch (Config.algorithmType) {
            case BACKPACK:
                return new BackpackChromosomeController();
            case SALESMAN:
                return new SalesmanChromosomeController();
            default:
                throw new RuntimeException();
        }
    }

    private void initSelection(Random random) {
        switch (Config.selectionType) {
            case HALF_POPULATION:
                selection = new HalfPopulationSelection();
                break;
            case ONE_BEST_ONE_RANDOM:
                selection = new OneBestOneRandomSelection(random);
                break;
            case TWO_BEST:
                selection = new TwoBestSelection();
                break;
        }
    }

    private void initCrossover(ChromosomeController chromosomeController, Random random) {
        switch (Config.crossoverType) {
            case FAIR_POINT:
                crossover = new FairPointCrossover(chromosomeController);
                break;
            case RANDOM_POINT:
                crossover = new RandomPointCrossover(chromosomeController, random);
                break;
        }
    }

    private void initMutation(FitnessFunction fitnessFunction, Random random) {
        switch (Config.mutationType) {
            case SWAP_GENES:
                mutation = new SwapGenesMutation(fitnessFunction, random);
                break;
            case OPPOSITE_VALUE:
                mutation = new OppositeBooleanValueMutation(fitnessFunction, random);
                break;
        }
    }

    private void initGenerationReplacement() {
        if (Config.generationReplacementType == GenerationReplacementType.All_OFFSPRING_INTO_POPULATION) {
            generationReplacement = new AllOffspringIntoPopulationGenerationReplacement();
        }
    }

    private void sortPopulationBasedOnTaskType(Population population) {
        switch (Config.taskType) {
            case MAXIMIZATION:
                population.getIndividuals().sort(Comparator.comparingInt(Individual::getFitness));
                break;
            case MINIMIZATION:
                population.getIndividuals().sort((i1, i2) -> i2.getFitness() - i1.getFitness());
                break;
        }
    }

    private void changeBestIndividualBasedOnTaskTypeIfPossible(Population population) {
        Individual bestInPopulation = population.getIndividuals().get(population.getIndividuals().size() - 1);

        switch (Config.taskType) {
            case MAXIMIZATION:
                changeBestIndividualIfPossible(bestInPopulation, (ind) -> ind.getFitness() > bestIndividual.getFitness());
                break;
            case MINIMIZATION:
                changeBestIndividualIfPossible(bestInPopulation, (ind) -> ind.getFitness() < bestIndividual.getFitness());
                break;
        }
    }

    private void changeBestIndividualIfPossible(Individual individual, Predicate<Individual> predicate) {
        if (bestIndividual == null || predicate.test(individual)) {
            bestIndividual = individual;
            bestIndividualNotChangeCounter = 0;
        } else {
            bestIndividualNotChangeCounter++;
        }
    }

}
