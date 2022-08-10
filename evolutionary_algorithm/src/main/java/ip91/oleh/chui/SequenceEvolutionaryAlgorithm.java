package ip91.oleh.chui;

import ip91.oleh.chui.conditionData.BackpackConditionData;
import ip91.oleh.chui.conditionData.SalesmanConditionData;
import ip91.oleh.chui.model.Population;
import ip91.oleh.chui.model.Result;
import ip91.oleh.chui.model.RuntimeInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SequenceEvolutionaryAlgorithm {

    private final BackpackConditionData backpackConditionData;
    private final SalesmanConditionData salesmanConditionData;

    private final Population population;

    public void process() {
        EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(backpackConditionData, salesmanConditionData);
        evolutionaryAlgorithm.init();

        long startTime = System.currentTimeMillis();
        Result result = evolutionaryAlgorithm.run(population, new RuntimeInfo(null, 0));
        long finishTime = System.currentTimeMillis();

        System.out.println("Best fitness: " + result.getBestIndividual().getFitness());
        System.out.println("Generation: " + result.getGeneration());
        System.out.println("Execution time: " + (finishTime - startTime) + " ms");
    }

}
