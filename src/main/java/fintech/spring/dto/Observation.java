package fintech.spring.dto;

import fintech.spring.entity.FredData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// dto sevice <-> controaller <-> dto
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Observation {

    String date;
    Double value;

    public static Observation fromFredData(FredData fredData){
        return new Observation(fredData.getObservationDate(), fredData.getValue());
    }

}
