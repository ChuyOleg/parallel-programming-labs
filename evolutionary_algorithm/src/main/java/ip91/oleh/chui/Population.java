package ip91.oleh.chui;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.TreeSet;


@AllArgsConstructor
@Getter
public class Population {

    private final TreeSet<Individual> individuals;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Individual individual : individuals) {
            builder.append(individual);
            builder.append("\n");
        }
        return builder.toString();
    }
}
