package ip91.oleh.chui.generationReplacement;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;

import java.util.List;

public class AllOffspringIntoPopulationGenerationReplacement implements GenerationReplacement {

    @Override
    public void process(Population population, List<Individual> offspring) {



        System.out.println("Population size -> " + population.getIndividuals().length);
        System.out.println("Offspring size -> " + offspring.size());
    }

}
