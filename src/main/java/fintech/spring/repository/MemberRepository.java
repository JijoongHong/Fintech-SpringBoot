package fintech.spring.repository;

import fintech.spring.entity.Member;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource 자동생성 repository 인터페이스 하나로
@Repository
public interface MemberRepository extends PagingAndSortingRepository<Member,Long> {
    //<대상, 킷값>
}
