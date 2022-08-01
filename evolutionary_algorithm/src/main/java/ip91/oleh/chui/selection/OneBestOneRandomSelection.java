package ip91.oleh.chui.selection;

import ip91.oleh.chui.Individual;
import ip91.oleh.chui.Population;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
public class OneBestOneRandomSelection implements Selection {

    private final Random random;

    @Override
    public List<Individual> process(Population population) {
        int randomNum = random.nextInt(population.getIndividuals().size() - 1);

        Individual theBestIndividual = population.getIndividuals().stream()
                .skip(population.getIndividuals().size() - 1).findFirst().orElseThrow(RuntimeException::new);

        Individual randomIndividual = population.getIndividuals().stream()
                .skip(randomNum).findFirst().orElseThrow(RuntimeException::new);

        return Arrays.asList(randomIndividual, theBestIndividual);
    }

}
