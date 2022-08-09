package ip91.oleh.chui.selection;

import ip91.oleh.chui.model.Individual;
import ip91.oleh.chui.model.Population;

import java.util.List;

public interface Selection {

    List<Individual> process(Population population);

}
