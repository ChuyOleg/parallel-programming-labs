package ip91.oleh.chui.conditionData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class BackpackConditionData {

    private final int[] weightTable;
    private final int[] priceTable;
    private final int maxWeight;

}
