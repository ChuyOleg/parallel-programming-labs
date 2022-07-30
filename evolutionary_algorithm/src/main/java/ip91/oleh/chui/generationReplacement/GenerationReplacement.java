package ip91.oleh.chui.generationReplacement;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;

import java.util.List;

public interface GenerationReplacement {

    void process(Population population, List<Individual> offspring);

}
