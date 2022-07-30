package ip91.oleh.chui.selection;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;

import java.util.List;

public interface Selection {

    List<Individual> process(Population population);

}
