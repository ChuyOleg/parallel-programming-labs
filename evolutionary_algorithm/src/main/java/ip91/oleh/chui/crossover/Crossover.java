package ip91.oleh.chui.crossover;

import ip91.oleh.chui.model.Individual;

import java.util.List;

public interface Crossover {

    List<Individual> process(List<Individual> individuals);

}
