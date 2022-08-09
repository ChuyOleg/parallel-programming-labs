package ip91.oleh.chui.mutation;

import ip91.oleh.chui.model.Individual;

import java.util.List;

public interface Mutation {

    void process(List<Individual> individuals);

}
