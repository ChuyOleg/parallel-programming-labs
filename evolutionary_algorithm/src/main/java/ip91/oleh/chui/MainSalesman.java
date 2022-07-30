package ip91.oleh.chui;

import ip91.oleh.chui.conditionData.ConditionDataGenerator;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import ip91.oleh.chui.config.Config;
import ip91.oleh.chui.crossover.Crossover;
import ip91.oleh.chui.crossover.FairPointCrossover;
import ip91.oleh.chui.crossover.chromosomeController.ChromosomeController;
import ip91.oleh.chui.crossover.chromosomeController.SalesmanChromosomeController;
import ip91.oleh.chui.fitnessFunction.SalesmanFitnessFunction;
import ip91.oleh.chui.mutation.Mutation;
import ip91.oleh.chui.mutation.OppositeBooleanValueMutation;
import ip91.oleh.chui.mutation.SwapGenesMutation;
import ip91.oleh.chui.populationGenerator.PopulationGenerator;
import ip91.oleh.chui.populationGenerator.SalesmanPopulationGenerator;
import ip91.oleh.chui.selection.HalfPopulationSelection;
import ip91.oleh.chui.selection.OneBestOneRandomSelection;
import ip91.oleh.chui.selection.Selection;
import ip91.oleh.chui.selection.TwoBestSelection;

import java.util.List;

public class MainSalesman {

    public static void main(String[] args) {
        SalesmanConditionData salesmanData = ConditionDataGenerator.salesman();
        SalesmanFitnessFunction salesmanFitnessFunction = new SalesmanFitnessFunction(salesmanData);

        PopulationGenerator populationGenerator = new SalesmanPopulationGenerator(salesmanData);
        Population population = populationGenerator.generate(Config.POPULATION_SIZE);

        Selection twoBestSelection = new TwoBestSelection();
        Selection oneBestOneRandomSelection = new OneBestOneRandomSelection();
        Selection halfPopulationSelection = new HalfPopulationSelection();

        List<Individual> sample_1 = twoBestSelection.process(population);
        List<Individual> sample_2 = oneBestOneRandomSelection.process(population);
        List<Individual> sample_3 = halfPopulationSelection.process(population);

        ChromosomeController chromosomeController = new SalesmanChromosomeController();

        Crossover fairCrossover = new FairPointCrossover(salesmanFitnessFunction, chromosomeController);
//        Crossover fairCrossover = new SalesmanFairCrossover(salesmanFitnessFunction);
//        Crossover fairCrossover = new FairCrossover(salesmanFitnessFunction);
//        Crossover randomPointCrossover = new RandomPointCrossover(salesmanFitnessFunction);

        Mutation oppositeBooleanValueMutation = new OppositeBooleanValueMutation(salesmanFitnessFunction);
        Mutation swapGenesMutation = new SwapGenesMutation(salesmanFitnessFunction);

        System.out.println(salesmanData);
        System.out.println(population);
        System.out.println(sample_1);

        List<Individual> offspring = fairCrossover.process(sample_1);
//        List<MyIndividual> offspring = randomPointCrossover.process(sample_1);

        System.out.println(offspring);

        swapGenesMutation.process(offspring);

        System.out.println(offspring);
    }

}
