package ip91.oleh.chui;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class Population {

    private final Individual[] individuals;

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
