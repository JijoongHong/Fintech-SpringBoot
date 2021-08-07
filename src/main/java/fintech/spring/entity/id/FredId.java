package fintech.spring.entity.id;

import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;

@Data
public class FredId implements Serializable {
    String seriesId;
    String observationDate;
    //@Transient 직렬화 무시
}
