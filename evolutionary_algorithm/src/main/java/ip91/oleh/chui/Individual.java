package ip91.oleh.chui;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Individual {

    private final Object[] chromosome;
    private int fitness;

}
