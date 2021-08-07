package fintech.spring.repository;

import fintech.spring.entity.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@RepositoryRestResource 자동생성 repository 인터페이스 하나로
@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member,Long> {
    //<대상, 킷값>
    Optional<Member> findBySeqAndName(Long key, String name);

    @Query("SELECT m.org, count(m.seq) FROM Member m where m.active = ?1 group by m.org")
    List<Object> countOrgGroup(Boolean isActive);
}
