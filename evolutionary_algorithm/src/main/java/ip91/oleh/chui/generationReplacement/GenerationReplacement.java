package ip91.oleh.chui.generationReplacement;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.model.Population;

import java.util.List;

public interface GenerationReplacement {

    void process(Population population, List<Individual> offspring);

}
