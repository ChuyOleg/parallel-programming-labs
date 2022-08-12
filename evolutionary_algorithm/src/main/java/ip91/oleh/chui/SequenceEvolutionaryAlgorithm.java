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

    public Result process() {
        EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(backpackConditionData, salesmanConditionData);
        evolutionaryAlgorithm.init();

        return evolutionaryAlgorithm.run(population, new RuntimeInfo(null, 0));
    }

}
