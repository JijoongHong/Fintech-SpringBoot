package fintech.spring.repository;

import fintech.spring.entity.FredData;
import fintech.spring.entity.Member;
import fintech.spring.entity.id.FredId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FredRepository extends CrudRepository<FredData, FredId> {
    public List<FredData> findAllBySeriesIdAndObservationDateAfterObservationDateBefore(String seriesId, String from, String to);
}
